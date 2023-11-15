 package coffeeRecommendation.dal;
 import coffeeRecommendation.model.*;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;

public class LocationDao {
    protected ConnectionManager connectionManager;//

    private static LocationDao instance = null;

    protected LocationDao() {
        connectionManager = new ConnectionManager();
    }

    public static LocationDao getInstance() {
        if (instance == null) {
            instance = new LocationDao();
        }
        return instance;
    }

    // create
    public Location create(Location location) throws SQLException {

        String insertLocation = "INSERT INTO Location(LocationName) VALUES(?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertLocation);
            insertStmt.setString(1, location.getLocationName());
            insertStmt.executeUpdate();
            return location;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    // delete
    public Location delete(Location location) throws SQLException {
        String deleteLocation = "DELETE FROM Location WHERE LocationName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteLocation);
            deleteStmt.setString(1, location.getLocationName());
            deleteStmt.executeUpdate();
            // return null so the caller can no longer operate on the Location instance.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

    // read
    public Location getLocationByLocationName(String locationName) throws SQLException {
        String selectLocation = "SELECT LocationName FROM Location WHERE LocationName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectLocation);
            selectStmt.setString(1, locationName);
            results = selectStmt.executeQuery();

            if (results.next()) {
                return new Location(locationName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }
}