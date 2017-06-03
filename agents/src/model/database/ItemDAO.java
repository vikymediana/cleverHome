package model.database;

import java.sql.*;

/**
 * Created by victoria on 3/06/17.
 */
public class ItemDAO {

    private String updateStatus = "UPDATE " + DatabaseConstants.dbSchema + ".ITEM SET status = ? WHERE id = ?";

    public void updateStatus(String itemId, String newStatus) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseConstants.dbUrl, DatabaseConstants.dbUser, DatabaseConstants.dbPass);

            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(updateStatus);
            pstmt.setString(1, newStatus);
            pstmt.setString(2, itemId);
            pstmt.executeUpdate();
            pstmt.close();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
