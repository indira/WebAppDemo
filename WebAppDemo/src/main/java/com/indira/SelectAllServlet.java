package com.indira;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.indira.entity.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectAllServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			// Step1: register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step2: Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			System.out.println("Connection created");

			// Step3: Create PreparedStatement for insert query
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMP");
			
			// Step 4: Execute the INSERT query
			ResultSet result = ps.executeQuery();
			List<Employee> list = new ArrayList<>();
			while (result.next()) {
				int i = result.getInt("id");
				String name = result.getString("name");
				double sal = result.getDouble("salary");
				Employee e = new Employee(i, name, sal);
				list.add(e);
			} 
			req.setAttribute("emplist", list);
			RequestDispatcher rd = req.getRequestDispatcher("displayall.jsp");
			rd.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
