package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.indira.dao.EmpDao;
import com.indira.entity.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		double sal = Double.parseDouble(req.getParameter("salary"));
		Employee e = new Employee(id,name,sal);
		EmpDao dao = new EmpDao();
		Boolean isAdded = dao.insert(e);
		if(isAdded) {
			req.setAttribute("msg", "Data successfully inserted");
			RequestDispatcher rd = req.getRequestDispatcher("success.jsp");
			rd.forward(req, res);
		} else {
			req.setAttribute("errorMsg","Data couldn't be inserted");
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, res);
		}
		
	}
}