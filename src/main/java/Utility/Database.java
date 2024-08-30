package Utility;

import java.sql.*;

public class Database {
	private static Connection connection;
	static{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkingManagement","root","meet21");
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
