package recommendation.model;

import java.util.Date;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Admins extends Persons {
	protected Date lastLogin;
	
	
	public Admins(String userName, String userPassword, String email, String phoneNumber, Date lastLogin) {
		super(userName, userPassword, email, phoneNumber);
		this.lastLogin = lastLogin;
	}
	
	public Admins(String userName) {
		super(userName);
	}

	/** Getters and setters. */
	
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}	
	
}
