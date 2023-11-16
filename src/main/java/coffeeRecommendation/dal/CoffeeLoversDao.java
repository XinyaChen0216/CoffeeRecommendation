package recommendation.dal;

import recommendation.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;


public class CoffeeLoversDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static CoffeeLoversDao instance = null;
	protected CoffeeLoversDao() {
		super();
	}
	public static CoffeeLoversDao getInstance() {
		if(instance == null) {
			instance = new CoffeeLoversDao();
		}
		return instance;
	}

	public CoffeeLovers create(CoffeeLovers coffeeLover) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(coffeeLover.getUserName(), coffeeLover.getUserPassword(),coffeeLover.getEmail(),
				coffeeLover.getPhoneNumber()));

		String insertCoffeeLover = "INSERT INTO CoffeeLovers(UserName,Experience,StatusLevel) "
				+ "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCoffeeLover);
			insertStmt.setString(1, coffeeLover.getUserName());
			insertStmt.setInt(2, coffeeLover.getExperience());
			insertStmt.setString(3, coffeeLover.getStatusLevel().name());
			insertStmt.executeUpdate();
			return coffeeLover;
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
		}
	}
	
	
	/**
	 * Update the Phone number of the CoffeeLovers instance.
	 * This runs a UPDATE statement.
	 */
	public CoffeeLovers updatePhoneNumber(CoffeeLovers coffeeLover, String newPhoneNumber) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updatePhoneNumber(coffeeLover, newPhoneNumber);
		return coffeeLover;
	}

	/**
	 * This runs a DELETE statement.
	 */
	public CoffeeLovers delete(CoffeeLovers coffeeLover) throws SQLException {
		String deleteCoffeeLover = "DELETE FROM CoffeeLovers WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCoffeeLover);
			deleteStmt.setString(1, coffeeLover.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + coffeeLover.getUserName());
			}

			super.delete(coffeeLover);

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
	

	public CoffeeLovers getCoffeeLoverFromUserName(String userName) throws SQLException {
		String selectCoffeeLover =
			"SELECT CoffeeLovers.UserName AS UserName,UserPassword,Email,PhoneNumber,Experience,StatusLevel " +
			"FROM CoffeeLovers INNER JOIN Persons " +
			"  ON CoffeeLovers.UserName = Persons.UserName " +
			"WHERE CoffeeLovers.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCoffeeLover);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String userPassword = results.getString("UserPassword");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				int experience = results.getInt("Experience");
				CoffeeLovers.StatusLevel statusLevel = CoffeeLovers.StatusLevel.valueOf(
						results.getString("StatusLevel"));
				CoffeeLovers coffeeLover = new CoffeeLovers(resultUserName, userPassword, email, phoneNumber, 
						experience,statusLevel);
				return coffeeLover;
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

}
