package coffeeReview.model;

import java.sql.Timestamp;

public class Reviews {
	int reviewID;
	Timestamp created;
	String  content;
	String userName;
	Double rating;
	String coffeeName;
	public Reviews(Timestamp created, String content, String userName, Double rating, String coffeeName) {
		super();
		this.created = created;
		this.content = content;
		this.userName = userName;
		this.rating = rating;
		this.coffeeName = coffeeName;
	}
	public Reviews(int reviewID, Timestamp created, String content, String userName, Double rating, String coffeeName) {
		super();
		this.reviewID = reviewID;
		this.created = created;
		this.content = content;
		this.userName = userName;
		this.rating = rating;
		this.coffeeName = coffeeName;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getCoffeeName() {
		return coffeeName;
	}
	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}
	
	
	
}
