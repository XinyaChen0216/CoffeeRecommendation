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


public class ProfessionalsDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static ProfessionalsDao instance = null;
	protected ProfessionalsDao() {
		super();
	}
	public static ProfessionalsDao getInstance() {
		if(instance == null) {
			instance = new ProfessionalsDao();
		}
		return instance;
	}

	public Professionals create(Professionals professional) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(professional.getUserName(), professional.getUserPassword(),professional.getEmail(),
				professional.getPhoneNumber()));

		String insertProfessional = "INSERT INTO Professionals(UserName,HasCredential,Experience,StatusLevel) "
				+ "VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertProfessional);
			insertStmt.setString(1, professional.getUserName());
			insertStmt.setBoolean(2, professional.isHasCredential());
			insertStmt.setInt(3, professional.getExperience());
			insertStmt.setString(4, professional.getStatusLevel().name());
			insertStmt.executeUpdate();
			return professional;
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
	 * Update the Phone number of the Professionals instance.
	 * This runs a UPDATE statement.
	 */
	public Professionals updatePhoneNumber(Professionals professional, String newPhoneNumber) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updatePhoneNumber(professional, newPhoneNumber);
		return professional;
	}

	/**
	 * This runs a DELETE statement.
	 */
	public Professionals delete(Professionals professional) throws SQLException {
		String deleteProfessional = "DELETE FROM Professionals WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProfessional);
			deleteStmt.setString(1, professional.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + professional.getUserName());
			}

			super.delete(professional);

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
	

	public Professionals getProfessionalFromUserName(String userName) throws SQLException {
		String selectProfessional =
			"SELECT Professionals.UserName AS UserName,UserPassword,Email,PhoneNumber,HasCredential,"
			+ "Experience,StatusLevel " +
			"FROM Professionals INNER JOIN Persons " +
			"  ON Professionals.UserName = Persons.UserName " +
			"WHERE Professionals.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessional);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String userPassword = results.getString("UserPassword");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Boolean hasCredential = results.getBoolean("HasCredential");
				int experience = results.getInt("Experience");
				Professionals.StatusLevel statusLevel = Professionals.StatusLevel.valueOf(
						results.getString("StatusLevel"));
				Professionals professional = new Professionals(resultUserName, userPassword, email, phoneNumber, 
						hasCredential,experience,statusLevel);
				return professional;
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
