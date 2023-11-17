package recommendation.dal;
import recommendation.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OriginDao {
    protected ConnectionManager connectionManager;//

    private static OriginDao instance = null;

    protected OriginDao() {
        connectionManager = new ConnectionManager();
    }

    public static OriginDao getInstance() {
        if (instance == null) {
            instance = new OriginDao();
        }
        return instance;
    }

    // create
    public Origin create(Origin origin) throws SQLException {

        String insertOrigin = "INSERT INTO Origin(OriginName) VALUES(?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertOrigin);
            insertStmt.setString(1, origin.getOriginName());
            insertStmt.executeUpdate();
            return origin;
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
    public Origin delete(Origin origin) throws SQLException {
        String deleteOrigin = "DELETE FROM Origin WHERE OriginName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteOrigin);
            deleteStmt.setString(1, origin.getOriginName());
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
    public Origin getOriginByOriginName(String originName) throws SQLException {
        String selectOrigin = "SELECT OriginName FROM Origin WHERE OriginName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectOrigin);
            selectStmt.setString(1, originName);
            results = selectStmt.executeQuery();

            if (results.next()) {
                return new Origin(originName);
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