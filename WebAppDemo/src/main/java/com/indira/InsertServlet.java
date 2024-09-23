package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		double sal = Double.parseDouble(req.getParameter("salary"));
		try {
			// Step1: register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step2: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			System.out.println("Connection created");

			// Step3: Create PreparedStatement for insert query
			PreparedStatement ps = con.prepareStatement("INSERT INTO EMP values(?,?,?)");

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setDouble(3, sal);

			// Step 4: Execute the INSERT query
			int rowsAffected = ps.executeUpdate();
			System.out.println(rowsAffected + " record(s) inserted.");

			// Step5 close the connection
			con.close();
			System.out.println("Connection Closed");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		res.setContentType( "text/html" );
		PrintWriter pw = res.getWriter();
		pw.write( "<h3 style=color:'green'>Insertion successfull</h3>" );
		pw.close();
		
	}
}