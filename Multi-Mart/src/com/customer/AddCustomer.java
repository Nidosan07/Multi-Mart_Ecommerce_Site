package com.customer;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AddCustomer
 */
@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		int id = rnd.nextInt(9000) + 10000;
		System.out.println("Customer Id  "+id);
		while (salt.length() < 3) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String code = salt.toString();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String pincode = request.getParameter("pincode");
		HttpSession hs=request.getSession();
		try {
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tblcustomer(id,address,email,gender,name,password,phone,valid,pin_code)values('" + id
							+ "','" + address + "','" + email + "','" + gender + "','" + name + "','" + password + "','"
							+ mobile + "','" + code + "','" + pincode + "')");
			if (addCustomer > 0) {
				String message="Customer register successfully.";
				hs.setAttribute("success-message", message);
				response.sendRedirect("customer-register.jsp");
			} else {
				String message="Customer registration fail";
				hs.setAttribute("fail-message", message);
				response.sendRedirect("customer-register.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
