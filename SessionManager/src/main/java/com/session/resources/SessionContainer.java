package com.session.resources;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import com.session.config.Configuration;

public class SessionContainer {
	
	private static SessionContainer sessionContainer = null;
	private ConcurrentMap<String,Session> sessionMap = null;
	private static long sessionTimeout = Integer.parseInt(Configuration.getProperty(Constants.DEFAULT_SESSION_TIMEOUT)) * 60
			* 1000;
	private static long sessioncleanuptimeInterval = Integer.parseInt(Configuration.getProperty(Constants.SESSION_CLEANUP_TASK_TIME)) * 60
			* 1000;
	
	private SessionContainer() {
		sessionMap = new ConcurrentHashMap<>();
		//Timer task which invalidates and removes invalid sessions
		Timer sessionCleanupTimer = new Timer();
		sessionCleanupTimer.schedule(new SessionCleanupTimerTask(), 0,
				sessioncleanuptimeInterval); 
	}
	
	/* Method to add new session to Map */
	public static SessionContainer getInstance() {
		if(sessionContainer == null) {
			sessionContainer = new SessionContainer();
		}
		return sessionContainer;
	}
	
	
	public void addSession(String sessionId, Session sessionDetails) {
		sessionMap.put(sessionId, sessionDetails);
	}
	
	/*Checks if the session is valid */
	public boolean validateSession(String sessionId) {
		Session session = sessionMap.get(sessionId);
		if(session == null) {
			return false;
		}else if(System.currentTimeMillis() - session.getLastAccessTime().getTime()  > sessionTimeout) {
			return false;
		}
		return true;
	}
	
	/* Removes all invalid sessions 
	 * This should be run at regular intervals to check for stale session objects 
     */
	public void removeExpiredSessions() {
		sessionMap = sessionMap.entrySet().stream()
				.filter(x -> System.currentTimeMillis() - x.getValue().getLastAccessTime().getTime() < sessionTimeout )
				.collect(Collectors.toConcurrentMap(x -> x.getKey(), x -> x.getValue()));
		
	}
	
	/*Invalidates the session for the given sessionId */
	public void invalidateSession(String sessionId) {
		sessionMap.remove(sessionId);
	}
	
	/*Timer task to clear stale sessions */
	class SessionCleanupTimerTask extends TimerTask{
		@Override
		public void run() {
			removeExpiredSessions();
		}
	}
	
}


