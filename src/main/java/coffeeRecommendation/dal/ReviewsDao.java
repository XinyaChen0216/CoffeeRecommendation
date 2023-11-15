package coffeeReview.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import coffeeReview.model.Reviews;

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
			"INSERT INTO Reviews(created,content,userName,restaurantID,rating) " +
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
			insertStmt.setInt(4, review.getRestaurantID());
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
}
