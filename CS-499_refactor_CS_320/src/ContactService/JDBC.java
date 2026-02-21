package ContactService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    /**
     * Returns a JDBC connection to the database.
     * Does NOT execute any queries — queries belong in ContactQueries.
     */
    public Connection DBresource() {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database. The connection establishes the database session
            Connection con = DriverManager.getConnection(
            	//not a safe practice or modular, used for testing connection
                "jdbc:mysql://localhost:3306/contact_data",
                "root",
                "password"
            );
            //never reached, need to fix
            if (con != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Failed to connect!");
            }
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    /** Close any created Statement safely */
    public static void closeConnection(java.sql.Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception while closing statement");
            }
        }
    }

    /** Close the database connection for MySQL safely */
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception while closing connection");
            }
        }
    }

    /** Close a ResultSet safely */
    public static void close(java.sql.ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception while closing ResultSet");
            }
        }
    }
}
