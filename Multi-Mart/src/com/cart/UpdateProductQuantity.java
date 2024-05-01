package com.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import com.connection.DatabaseConnection;

@WebServlet("/UpdateProductQuantity")
public class UpdateProductQuantity extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		int productId=Integer.parseInt(request.getParameter("productId"));
		HttpSession session=request.getSession();
		String discount_price=null;
		Double productPrice=0.0;
		try {
			ResultSet rs=DatabaseConnection.getResultFromSqlQuery("select discount_price from tblcart where customer_id='"+session.getAttribute("id")+"' and product_id='"+productId+"'");
			while(rs.next()) {
				discount_price=rs.getString("discount_price");
				productPrice=Double.parseDouble(discount_price); 
			}
			productPrice=productPrice*quantity;
			int updateQuantity=DatabaseConnection.insertUpdateFromSqlQuery("update tblcart set quantity='"+quantity+"',total_price='"+productPrice+"' where customer_id='"+session.getAttribute("id")+"' and product_id='"+productId+"' ");
			if(updateQuantity>0) {
				response.sendRedirect("checkout.jsp");
			}else {
				response.sendRedirect("checkout.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
