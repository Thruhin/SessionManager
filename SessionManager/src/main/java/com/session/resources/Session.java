package com.session.resources;

import java.sql.Timestamp;

public class Session {
	
	private Timestamp lastAccessTime;

	public Timestamp getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Timestamp lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

}
