package com.connection;

/*
 * import org.apache.commons.dbcp.BasicDataSource; import
 * java.security.SecureRandom; import java.sql.*; import java.util.Random;
 * 
 * public class DatabaseConnection { static Connection con;
 * 
 * public static Connection getConnection() throws Exception {
 * Class.forName("com.mysql.cj.jdbc.Driver"); return
 * DriverManager.getConnection(
 * "jdbc:mysql://localhost:3306/onlineshoppingsystem?useSSL=false", "root",
 * "root"); }
 * 
 * public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
 * ResultSet rs = null; try { con = getConnection(); rs =
 * con.createStatement().executeQuery(SqlQueryString); } catch (Exception ex) {
 * ex.printStackTrace(); } return rs; }
 * 
 * public static int insertUpdateFromSqlQuery(String SqlQueryString) { int
 * result=0; try { if (con == null) { getConnection(); }
 * System.out.println("Query :: "+SqlQueryString);
 * result=con.createStatement().executeUpdate(SqlQueryString); } catch
 * (Exception ex) { ex.printStackTrace(); } return result; }
 * 
 * public static String randompasswordgeneration() { Random RANDOM = new
 * SecureRandom(); final int PASSWORD_LENGTH = 10; String letters =
 * "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789@"; String password =
 * ""; for (int i = 0; i < PASSWORD_LENGTH; i++) { int index = (int)
 * (RANDOM.nextDouble() * letters.length()); password +=
 * letters.substring(index, index + 1); } return password; } }
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection connection;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingsystem", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (connection);
	}

	public static void CloseConnection() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
		ResultSet rs = null;
		try {
			if (connection == null) {
				getConnection();
			}
			rs = connection.createStatement().executeQuery(SqlQueryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static int insertUpdateFromSqlQuery(String SqlQueryString) {
		int i = 2;
		try {
			if (connection == null) {
				getConnection();
			}
			i = connection.createStatement().executeUpdate(SqlQueryString);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return i;
	}
}
