package com.rasnil.restful.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Util {
	
	public static void main(String[] args) {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection(
			"jdbc:hsqldb:hsql://localhost:9001/testdb", "sa", "");
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT firstname FROM user WHERE id='1'");
			while (resultSet.next()) {
				System.out.println("EMPLOYEE Salary:"
						+ resultSet.getString("Salary"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
