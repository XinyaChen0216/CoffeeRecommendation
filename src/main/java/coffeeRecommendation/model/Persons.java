package recommendation.model;


public class Persons {
	protected String userName;
	protected String userPassword;
	protected String email;
	protected String phoneNumber;
	
	public Persons(String userName, String userPassword, String email, String phoneNumber) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Persons(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
