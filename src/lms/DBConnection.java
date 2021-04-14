package lms;

import java.sql.*;

public class DBConnection{
	
	Connection con=null;
	Connection startConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Nvvrk@123");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("exception raised");
		}
		return con;
	}

	void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("exception raised");
		}
	}
	
}
