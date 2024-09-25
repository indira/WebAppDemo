package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.indira.dao.EmpDao;
import com.indira.entity.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectAllServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		EmpDao dao = new EmpDao();
		List<Employee> a = dao.SelectAll();
		if(a == null) {
			req.setAttribute("errorMsg", "No record found");
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, res);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("displayAll.jsp");
			rd.forward(req, res);
		}
			
	}
}
