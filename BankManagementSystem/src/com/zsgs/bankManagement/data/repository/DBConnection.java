package com.zsgs.bankManagement.data.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	//Static Utility Class
    private static final String URL = "jdbc:mysql://localhost:3306/bank_db";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

	private DBConnection() {}
	
	public static Connection getConnctionDB() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
