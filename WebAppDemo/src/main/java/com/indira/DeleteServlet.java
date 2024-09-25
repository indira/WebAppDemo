package com.indira;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.indira.dao.EmpDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		EmpDao dao = new EmpDao();
		boolean isDeleted = dao.delete(id);
		if(isDeleted) {
			req.setAttribute("msg", "Data deleted successfully");
			RequestDispatcher rd = req.getRequestDispatcher("success.jsp");
			rd.forward(req, res);
		} else {
			req.setAttribute("errorMsg","Data couldn't be deleted");
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, res);
		}
	}
}
