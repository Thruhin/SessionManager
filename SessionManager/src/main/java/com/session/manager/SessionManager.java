package com.session.manager;

import java.sql.Timestamp;

import com.session.resources.Session;
import com.session.resources.SessionContainer;
import com.session.util.SecureTokenGenerator;

public class SessionManager {

	private SecureTokenGenerator sessionIdGenerator = new SecureTokenGenerator();
	private SessionContainer sessionContainer = SessionContainer.getInstance();

	public String createSession() {
		String sessionId = sessionIdGenerator.generateToken(32);
		Session session = new Session();
		session.setLastAccessTime(new Timestamp(System.currentTimeMillis()));
		sessionContainer.addSession(sessionId, session);
		return sessionId;
	}

	// Checks if the session is valid
	public boolean validateSession(String sessionId) {
		return sessionContainer.validateSession(sessionId);
	}

	// Invalidates the session if available
	public boolean invalidateSession(String sessionId) {
		sessionContainer.invalidateSession(sessionId);
		return true;
	}
}
