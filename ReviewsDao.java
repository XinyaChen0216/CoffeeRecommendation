package coffeeReview.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import coffeeReview.model.Coffee;
import coffeeReview.model.Reviews;
import coffeeReview.model.Coffee.RoastType;

public class ReviewsDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
//	int reviewID, Timestamp created, String content, String userName, int restaurantID
	public  Reviews create(Reviews review)throws SQLException {
		String insertReviews =
			"INSERT INTO Reviews(created,content,userName,coffeeName,rating) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReviews,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, review.getCreated());
			insertStmt.setString(2, review.getContent());
			insertStmt.setString(3, review.getUserName());
			insertStmt.setString(4, review.getCoffeeName());
			insertStmt.setDouble(5, review.getRating());
			

			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reviewID = -1;
			if(resultKey.next()) {
				reviewID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewID(reviewID);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReviews = "SELECT * FROM Reviews WHERE reviewID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, reviewId);
		
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				Timestamp created = results.getTimestamp("created");
				String content = results.getString("content");
				String userName = results.getString("userName");
				String coffeeName = results.getString("coffeeName");
				Double rating = results.getDouble("rating");
				Reviews reviews = new Reviews(reviewId, created, content, userName, rating, coffeeName);
				return reviews;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public  List<Reviews> getReviewsByUserName(String userName) throws SQLException {
		List<Reviews> reviews = new ArrayList<>();
		String selectReviews =
			"SELECT * FROM Reviews WHERE UserName=?; " ;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int reviewID = results.getInt("reviewID");
				Timestamp created = results.getTimestamp("created");
				String content = results.getString("content");
				Double rating = results.getDouble("rating");
				String coffeeName = results.getString("coffeeName");

				Reviews selectReview = new Reviews(reviewID, created, content, userName, rating, coffeeName);

				reviews.add(selectReview);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
}
	
	
	public  List<Reviews> getReviewsByCoffeeName(String coffeeName) throws SQLException {

		List<Reviews> reviews = new ArrayList<>();
		String selectReviews =
			"SELECT * FROM Reviews WHERE coffeeName=?; " ;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, coffeeName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int reviewID = results.getInt("reviewID");
				Timestamp created = results.getTimestamp("created");
				String content = results.getString("content");
				String userName = results.getString("userName");
				Double rating = results.getDouble("rating");
				Reviews selectReview = new Reviews(reviewID, created, content, userName, rating, coffeeName);
				reviews.add(selectReview);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}

	
	public Double getAverageRating(String coffeeName) throws SQLException {
		String getAverageRating = "SELECT AVG(rating) AS averageRating FROM Reviews WHERE coffeeName=? ORDER BY averageRating DESC;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getAverageRating);
			selectStmt.setString(1, coffeeName);
			results = selectStmt.executeQuery();
			
			 if (results.next()) {
	                // Retrieve the average rating from the result set
	                return results.getDouble("averageRating");
	            }

	            // If no average rating is found, return 0 (or handle as needed)
	        return 0.0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
	}

		
	
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReviews = "DELETE FROM Reviews WHERE reviewID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReviews);
			deleteStmt.setInt(1, review.getReviewID());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for reviewID=" + review.getReviewID());
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
}
	//public Coffee(String coffeeName, RoastType roastType, Double price, String roasterName)
	public List<Coffee> getCoffeesWithKeywordsAndOrderByRating(String keywords) throws SQLException {
	    List<Coffee> coffees = new ArrayList<>();
	    String selectCoffees =
	    		"SELECT Coffee.CoffeeName, AVG(Rating) AS AverageRating, content,RoastType,roasterName,price " + 
	    		"FROM Coffee inner join Reviews " + 
	    		"on Coffee.coffeeName = Reviews.coffeename "+
	    		"WHERE Content LIKE ? " +
	    		"GROUP BY Coffee.CoffeeName,content,RoastType,roasterName,price " + 
	    		"ORDER BY AverageRating DESC;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCoffees);
			selectStmt.setString(1, keywords);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String roastType = results.getString("RoastType");
				String coffeeName = results.getString("coffeeName");
				String roasterName = results.getString("roasterName");
				Double price = results.getDouble("price");
				RoastType roastType2 = RoastType.valueOf(roastType);
				Coffee selectCoffee = new Coffee(coffeeName, roastType2, price, roasterName);
				coffees.add(selectCoffee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}

	    return coffees;
	}
	public List<Reviews> getReviewBykeywords(String keywords) throws SQLException {
	    List<Reviews> reviews = new ArrayList<>();
	    String selectCoffees =
	    		"SELECT CoffeeName,content " + 
	    		"FROM Reviews " + 
	    		"WHERE content LIKE ? " ;
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCoffees);
			selectStmt.setString(1, "%" + keywords + "%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				String coffeeName = results.getString("coffeeName");
				String content = results.getString("content");
				Reviews selectReview = new Reviews(content, coffeeName);
				reviews.add(selectReview);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}

	    return reviews;
	}
}
