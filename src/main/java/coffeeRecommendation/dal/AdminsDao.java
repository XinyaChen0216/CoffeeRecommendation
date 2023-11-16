package recommendation.dal;

import recommendation.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Admins table in your
 * MySQL instance. This is used to store {@link Admins} into your MySQL instance and 
 * retrieve {@link Admins} from MySQL instance.
 */
public class AdminsDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static AdminsDao instance = null;
	protected AdminsDao() {
		super();
	}
	public static AdminsDao getInstance() {
		if(instance == null) {
			instance = new AdminsDao();
		}
		return instance;
	}
	
	public Admins create(Admins admin) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(admin.getUserName(), admin.getUserPassword(),admin.getEmail(),admin.getPhoneNumber()));

		String insertAdmin = "INSERT INTO Admins(UserName,LastLogin) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdmin);
			insertStmt.setString(1, admin.getUserName());
			insertStmt.setTimestamp(2, new Timestamp(admin.getLastLogin().getTime()));
			insertStmt.executeUpdate();
			return admin;
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
	 * Update the Phone number of the Admins instance.
	 * This runs a UPDATE statement.
	 */
	public Admins updatePhoneNumber(Admins admin, String newPhoneNumber) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updatePhoneNumber(admin, newPhoneNumber);
		return admin;
	}
	

	/**
	 * Delete the Admins instance.
	 * This runs a DELETE statement.
	 */
	public Admins delete(Admins admin) throws SQLException {
		String deleteAdmin = "DELETE FROM Admins WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdmin);
			deleteStmt.setString(1, admin.getUserName());
			deleteStmt.executeUpdate();

			super.delete(admin);

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
	
	
	public Admins getAdminFromUserName(String userName) throws SQLException {
		// To build an Admin object, we need the Persons record, too.
		String selectAdmin =
			"SELECT Admins.UserName AS UserName, UserPassword, Email, PhoneNumber, LastLogin " +
			"FROM Admins INNER JOIN Persons " +
			"  ON Admins.UserName = Persons.UserName " +
			"WHERE Admins.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdmin);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String userPassword = results.getString("UserPassword");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Date lastLogin = new Date(results.getTimestamp("LastLogin").getTime());
				Admins admin = new Admins(resultUserName,  userPassword, email, phoneNumber, lastLogin);
				return admin;
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
