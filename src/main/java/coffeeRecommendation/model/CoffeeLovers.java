package recommendation.model;

//import java.util.Date;


public class CoffeeLovers extends Persons {
	protected int experience;
	protected StatusLevel statusLevel;
	
	
	public enum StatusLevel{
		novice, intermediate, advanced
	}


	public CoffeeLovers(String userName, String userPassword, String email, String phoneNumber, int experience,
			StatusLevel statusLevel) {
		super(userName, userPassword, email, phoneNumber);
		this.experience = experience;
		this.statusLevel = statusLevel;
	}


	public CoffeeLovers(String userName) {
		super(userName);
	}
	
	
	/** Getters and setters. */

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
