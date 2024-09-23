package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.indira.entity.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			// Step1: register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step2: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			System.out.println("Connection created");

			// Step3: Create PreparedStatement for insert query
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMP WHERE ID=?");
			ps.setInt(1, id);
			// Step 4: Execute the INSERT query
			ResultSet result = ps.executeQuery();
			PrintWriter pw = res.getWriter();
			if(result.next()) {
				int i = result.getInt("id");
				String name = result.getString("name");
				double sal = result.getDouble("salary");
				Employee e = new Employee(i,name,sal);
				req.setAttribute("emp", e);
				RequestDispatcher rd = req.getRequestDispatcher("display.jsp");
				rd.forward(req, res);
			} else {
				pw.write("<h3 style=color:'green'>No record found!!</h3>");
			}
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
