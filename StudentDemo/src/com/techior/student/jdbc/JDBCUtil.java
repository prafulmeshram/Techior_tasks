/**
 * 
 */
package com.techior.student.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 12-06-2020
 *
 */
public class JDBCUtil {

	static Connection connection = null;
	private static final String url = "jdbc:mysql://localhost:3306/db_techior_demo?createDatabaseIfNotExist=true";
	private static final String user = "root";
	// Enter Db Password
	private static final String password = "mysql";

	// Open The Connection
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Close The Connection
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
