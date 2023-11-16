package recommendation.model;

//import java.util.Date;


public class Professionals extends Persons {
	protected boolean hasCredential;
	protected int experience;
	protected StatusLevel statusLevel;
	
	
	public enum StatusLevel{
		novice, intermediate, advanced
	}
	
	
	public Professionals(String userName, String userPassword, String email, String phoneNumber, boolean hasCredential,
			int experience, StatusLevel statusLevel) {
		super(userName, userPassword, email, phoneNumber);
		this.hasCredential = hasCredential;
		this.experience = experience;
		this.statusLevel = statusLevel;
	}


	public Professionals(String userName) {
		super(userName);
	}
	
	
	/** Getters and setters. */

	public boolean isHasCredential() {
		return hasCredential;
	}


	public void setHasCredential(boolean hasCredential) {
		this.hasCredential = hasCredential;
	}


	public int getExperience() {
		return experience;
	}


	public void setExperience(int experience) {
		this.experience = experience;
	}


	public StatusLevel getStatusLevel() {
		return statusLevel;
	}


	public void setStatusLevel(StatusLevel statusLevel) {
		this.statusLevel = statusLevel;
	}

	
	
}
