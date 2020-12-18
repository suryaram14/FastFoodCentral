package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	Connection conn = null;
	
	// Connection will connect to mySQL database and return the connection
	// successfully if connector file is referenced in file

	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FastFoodCentral_database", "root", "1234");
			return conn;
		} catch (ClassNotFoundException | SQLException ex) {
			System.err.println("MySqlConnection : " + ex.getMessage());
			return null;
		}
	}
}
