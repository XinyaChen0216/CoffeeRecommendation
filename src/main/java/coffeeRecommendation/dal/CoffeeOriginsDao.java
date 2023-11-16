package coffee.dal;

import coffee.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CoffeeOriginsDao {
	protected ConnectionManager connectionManager;

	private static CoffeeOriginsDao instance = null;
	protected CoffeeOriginsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CoffeeOriginsDao getInstance() {
		if(instance == null) {
			instance = new CoffeeOriginsDao();
		}
		return instance;
	}

	public CoffeeOrigins create(CoffeeOrigins coffeeOrigin) throws SQLException {
		String insertCoffeeOrigin =
			"INSERT INTO CoffeeOrigins(CoffeeName,OriginName) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCoffeeOrigin,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, coffeeOrigin.getCoffee().getCoffeeName());
			insertStmt.setInt(2, coffeeOrigin.getOrigin().getOriginName());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int coffeeOriginId = -1;
			if(resultKey.next()) {
				coffeeOriginId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			coffeeOrigin.setCoffeeOriginId(coffeeOriginId);
			return coffeeOrigin;
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
	 * Delete the CoffeeOrigins instance.
	 * This runs a DELETE statement.
	 */
	public CoffeeOrigins delete(CoffeeOrigins coffeeOrigin) throws SQLException {
		String deleteCoffeeOrigin = "DELETE FROM Reshares WHERE ReshareId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCoffeeOrigin);
			deleteStmt.setInt(1, coffeeOrigin.getReshareId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
