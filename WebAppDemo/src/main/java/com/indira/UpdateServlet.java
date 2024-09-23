package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
	    double sal = Double.parseDouble(req.getParameter("salary"));
	    try {
	    	//Step1: register the driver
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	
	    	// Step2: Establish the connection
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
	    	System.out.println("Connection created");
	    	
	    	//Step3: Create PreparedStatement for Update
	    	PreparedStatement ps = con.prepareStatement("Update emp set salary=? where id=?");
	    	ps.setDouble(1, sal);
	    	ps.setInt(2, id);
	    	
	    	// Step 4: Execute the INSERT query
			int rowsAffected = ps.executeUpdate();
			System.out.println(rowsAffected + " record(s) updated.");
			// Step5 close the connection
			con.close();
			System.out.println("Connection Closed");

	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    res.setContentType( "text/html" );
		PrintWriter pw = res.getWriter();
		pw.write( "<h3 style=color:'green'>Update successfull.</h3>" );
		pw.close();
	}
}
