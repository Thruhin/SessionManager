import com.session.manager.SessionManager;

public class SessionTester {
	static SessionManager manager =  new SessionManager();

	public static void main(String[] args) {
		
		System.out.println("Sessions created :");
		String sessid1 = manager.createSession();
		System.out.println(sessid1);
		String sessid2 = manager.createSession();
		System.out.println(sessid2);
		String sessid3 = manager.createSession();
		System.out.println(sessid3);
		
		System.out.println("\nSessions validation :");
		printSesionValidity(sessid1);
		printSesionValidity(sessid2);
		printSesionValidity(sessid3);
		
		System.out.println("\nInvalidating Session ID : " + sessid1);
		manager.invalidateSession(sessid1);
		System.out.println("Checking Session ID " + sessid1 + " validity is  :  " +  manager.validateSession(sessid1));
		
        System.out.println("\nInvalidating Session ID : " + sessid2);
        manager.invalidateSession(sessid2);
		System.out.println("Checking Session ID " + sessid2 + " validity is  :  " +  manager.validateSession(sessid2));
		System.out.println("\nChecking Session ID " + sessid3 + " validity is  :  " +  manager.validateSession(sessid3));
		
	}
	
	static void printSesionValidity(String sessionId) {
		System.out.println( "Session id : " + sessionId + " validation : " + manager.validateSession(sessionId));
	}

}
