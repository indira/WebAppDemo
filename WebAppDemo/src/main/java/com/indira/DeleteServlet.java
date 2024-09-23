package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			// Step1: register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step2: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			System.out.println("Connection created");

			// Step3: Create PreparedStatement for insert query
			PreparedStatement ps = con.prepareStatement("DELETE FROM EMP WHERE id=?");
			ps.setInt(1, id);

			// Step 4: Execute the INSERT query
			int rowsAffected = ps.executeUpdate();
			System.out.println(rowsAffected + " record(s) deleted.");

			// Step5 close the connection
			con.close();
			System.out.println("Connection Closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.setContentType( "text/html" );
		PrintWriter pw = res.getWriter();
		pw.write( "<h3 style=color:'green'>Record deleted successfully.</h3>" );
		pw.close();
	}
}
