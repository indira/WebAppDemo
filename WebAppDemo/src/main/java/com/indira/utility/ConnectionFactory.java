package com.indira.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static Connection con = null;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step2: Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		return con;
	}

}
