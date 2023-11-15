 package coffeeRecommendation.dal;
 import coffeeRecommendation.model.*;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;

public class RoasterDao {
    protected ConnectionManager connectionManager;//

    private static RoasterDao instance = null;

    protected RoasterDao() {
        connectionManager = new ConnectionManager();
    }

    public static RoasterDao getInstance() {
        if (instance == null) {
            instance = new RoasterDao();
        }
        return instance;
    }

    // create
    public Roaster create(Roaster roaster) throws SQLException {

        String insertRoaster = "INSERT INTO Roaster(RoasterName, LocationName) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRoaster);
            insertStmt.setString(1, roaster.getCoffee().getRoasterName());
            insertStmt.setString(2, roaster.getLocation().getLocationName());
            insertStmt.executeUpdate();
            return roaster;
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
    public Roaster delete(Roaster roaster) throws SQLException {
        String deleteRoaster = "DELETE FROM Roaster WHERE RoasterName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRoaster);
            deleteStmt.setString(1, roaster.getCoffee().getRoasterName());
            deleteStmt.executeUpdate();
            // return null so the caller can no longer operate on the Roaster instance.
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
    public List<Roaster> getRoastersByLocationName(String locationName)
            throws SQLException {
        List<Roaster> roasters = new ArrayList<Roaster>();
        String selectRoasters = "SELECT RoasterName, LocationName FROM Roaster WHERE LocationName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRoasters);
            selectStmt.setString(1, locationName);
            results = selectStmt.executeQuery();

            while (results.next()) {
                Roaster roaster = new Roaster(results.getString("roasterName"), locationName);
                roasters.add(roaster);
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
        return roasters;
    }

    // read
    public Roaster getRoasterByCoffee(Coffee coffee) throws SQLException {
        String selectRoaster = "SELECT RoasterName, LocationName FROM Roaster WHERE RoasterName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRoaster);
            selectStmt.setInt(1, coffee.getRoasterName());
            results = selectStmt.executeQuery();

            if (results.next()) {
                String locationName = results.getString("LocationName");
                return new Roaster(coffee.getRoasterName(), locationName);
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

    // update
    public Roaster updateLocation(Roaster roaster, Location newLocation) throws SQLException {
        String updateRoaster = "UPDATE Roaster SET locationName=? WHERE locationName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateRoaster);
            updateStmt.setString(1, newLocation.getLocationName());
            updateStmt.setString(2, roaster.getLocation().getLocationName());
            updateStmt.executeUpdate();

            // Update the roaster param before returning to the caller.
            roaster.setLocation(newLocation);
            return roaster;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }
}