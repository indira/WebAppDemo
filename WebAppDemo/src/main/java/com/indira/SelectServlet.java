package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.indira.dao.EmpDao;
import com.indira.entity.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		EmpDao dao = new EmpDao();
		Employee e = dao.select(id);
		if(e == null) {
			req.setAttribute("errorMsg", "No record found");
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, res);
		} else {
			req.setAttribute("emp", e);
			RequestDispatcher rd = req.getRequestDispatcher("display.jsp");
			rd.forward(req, res);
		}
	}
}
