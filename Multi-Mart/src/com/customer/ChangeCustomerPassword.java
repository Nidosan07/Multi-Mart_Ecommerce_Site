package com.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.*;

/**
 * Servlet implementation class ChangeCustomerPassword
 */
@WebServlet("/ChangeCustomerPassword")
public class ChangeCustomerPassword extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpassword = request.getParameter("cpassword");
		String password = request.getParameter("password");
		String confpass = request.getParameter("confpass");
		String pass = "";
		HttpSession session = request.getSession();
		try {
			ResultSet resultset = DatabaseConnection.getResultFromSqlQuery("select password from tblcustomer where password='" + cpassword+ "' and name='" + session.getAttribute("name") + "'");
			if (resultset.next()) {
				pass = resultset.getString(1);
			}
			if (password.equals(confpass)) {
				if (pass.equals(cpassword)) {
					int i = DatabaseConnection.insertUpdateFromSqlQuery("update tblcustomer set password='" + password + "' where name='"+ session.getAttribute("name") + "' ");
					response.sendRedirect("changeCustomerPassword.jsp");
				} else {
					
					response.sendRedirect("changeCustomerPassword.jsp");
				}
			} else {
				response.sendRedirect("changeCustomerPassword.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
