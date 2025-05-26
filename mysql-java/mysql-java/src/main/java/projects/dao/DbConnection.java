package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import projects.exception.DbException;

public class DbConnection {
  private static String HOST = "localhost"; // Database host
  private static String PASSWORD = "projects"; // Database password
  private static int PORT = 3306; // Database port
  private static String SCHEMA = "projects"; // Database schema
  private static String USER = "projects"; // Database username

  public static Connection getConnection() {
    // Construct the JDBC URI
    String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA,
        USER, PASSWORD);

    try {
      Connection conn = DriverManager.getConnection(uri); // Establish connection
      System.out.println("Connection to schema '" + SCHEMA + "' is successful."); // Success message
      return conn; // Return connection
    } catch (SQLException e) {
      System.out.println("Unable to get connection at " + uri); // Error message
      throw new DbException("Unable to get connection at \" + uri"); // Throw custom exception
    }
  }
}
