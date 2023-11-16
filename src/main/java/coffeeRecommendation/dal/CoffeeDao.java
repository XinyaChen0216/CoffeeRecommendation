package coffee.dal;

import coffee.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CoffeeDao {
	protected ConnectionManager connectionManager;

	private static CoffeeDao instance = null;
	protected CoffeeDao() {
		connectionManager = new ConnectionManager();
	}
	public static CoffeeDao getInstance() {
		if(instance == null) {
			instance = new CoffeeDao();
		}
		return instance;
	}

	public Coffee create(Coffee coffee) throws SQLException {
		String insertCoffee =
			"INSERT INTO Coffee(CoffeeName,RoastType,Price,RoasterName) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertCoffee);
				//Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, coffee.getCoffeeName());
			// Note: for the sake of simplicity, just set Picture to null for now.
			insertStmt.setString(2, coffee.getRoastType().name());
			insertStmt.setDouble(3, coffee.getPrice());
			insertStmt.setString(4, coffee.getRoasterName());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			//resultKey = insertStmt.getGeneratedKeys();
			//int postId = -1;
			//if(resultKey.next()) {
				//postId = resultKey.getInt(1);
			//} else {
				//throw new SQLException("Unable to retrieve auto-generated key.");
			//}
			//blogPost.setPostId(postId);
			return coffee;
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

	/**
	 * Update the price of the Coffee instance.
	 * This runs a UPDATE statement.
	 */
	public Coffee updateContent(Coffee coffee, Double newPrice) throws SQLException {
		String updateCoffee = "UPDATE Coffee SET Price=? WHERE CoffeeName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCoffee);
			updateStmt.setDouble(1, newPrice);
			updateStmt.setString(2, coffee.getCoffeeName());
			updateStmt.executeUpdate();

			// Update the coffee param before returning to the caller.
			coffee.setPrice(newPrice);
			return coffee;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the BlogPosts instance.
	 * This runs a DELETE statement.
	 */
	public Coffee delete(Coffee coffee) throws SQLException {
		String deleteCoffee = "DELETE FROM Coffee WHERE CoffeeName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCoffee);
			deleteStmt.setString(1, coffee.getCoffeeName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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

	/**
	 * Get the Coffee record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Coffee instance.
	 * Note that we use CoffeeDao to retrieve the referenced Coffee instance.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the Coffee, Origin tables and then build each object.
	 */
	public Coffee getCoffeeByCoffeeName(int coffeeName) throws SQLException {
		String selectCoffee =
			"SELECT CoffeeName,RoastType,Price,RoasterName" +
			"FROM Coffee " +
			"WHERE CoffeeName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCoffee);
			selectStmt.setInt(1, coffeeName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultCoffeeName = results.getString("CoffeeName");
				Coffee.RoastType roastType = Coffee.RoastType.valueOf(
						results.getString("RoastType"));
				Double price = results.getDouble("Price");
				String roasterName = results.getString("RoasterName");
				
				Coffee coffee = new Coffee(resultCoffeeName, roastType, price, roasterName);
				return coffee;
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

	/**
	 * Get Coffee by Origin.
	 */
	public List<Coffee> getCoffeeByOrigin(CoffeeOrigins coffeeOrigin) throws SQLException {
		List<Coffee> coffees = new ArrayList<Coffee>();
		String selectCoffees =
			"SELECT CoffeeName,RoastType,Price,RoasterName" +
			"FROM Coffee " +
	        "JOIN CoffeeOrigins ON Coffee.CoffeeName = CoffeeOrigins.OriginName"+
			"WHERE CoffeeOrigin.OriginName=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCoffees);
			selectStmt.setString(1, coffeeOrigin.getOrigin().getOrginName());
			results = selectStmt.executeQuery();
			if(results.next()) {
				String coffeeName = results.getString("CoffeeName");
				Coffee.RoastType roastType = Coffee.RoastType.valueOf(
						results.getString("RoastType"));
				Double price = results.getDouble("Price");
				String roasterName = results.getString("RoasterName");
				
				Coffee coffee = new Coffee(coffeeName, roastType, price, roasterName);
				
				coffees.add(coffee);
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
}
