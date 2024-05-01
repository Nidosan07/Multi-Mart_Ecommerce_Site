package com.contact;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class Contact
 */
@WebServlet("/Contact")
public class Contact extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		try {
			int addContact = DatabaseConnection.insertUpdateFromSqlQuery("insert into tblcontact(id,email,message,name,subject) values('" + id+ "','" + email + "','" + message + "','" + name + "','" + subject + "')");
			if (addContact > 0) {
				response.sendRedirect("contact.jsp");
			} else {
				response.sendRedirect("contact.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
