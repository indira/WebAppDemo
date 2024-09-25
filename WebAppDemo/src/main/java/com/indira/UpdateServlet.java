package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
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

public class UpdateServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
	    double sal = Double.parseDouble(req.getParameter("salary"));
		EmpDao dao = new EmpDao();
		Boolean isUpdated = dao.update(sal, id);
		if(isUpdated) {
			req.setAttribute("msg", "Salary successfully updated");
			RequestDispatcher rd = req.getRequestDispatcher("success.jsp");
			rd.forward(req, res);
		} else {
			req.setAttribute("errorMsg","Salary couldn't be updated");
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, res);
		}
	}
}
