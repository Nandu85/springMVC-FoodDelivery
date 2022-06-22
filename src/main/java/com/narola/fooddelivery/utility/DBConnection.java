package com.narola.fooddelivery.utility;

import com.narola.fooddelivery.exception.DatabaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;


public class DBConnection {

	private static DBConnection dbConnection = null;

	private static Connection connection = null;

	private static String dbname;

	private static String url;

	private static String username;

	private static String password;

	public static DBConnection getInstance() {
		if(dbConnection==null) {
			dbConnection=new DBConnection();
		}
			return dbConnection;
	}

	public DBConnection() {
	}

	public DBConnection(String dbname,String url,String username, String password) {
		this.dbname = dbname;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() throws DatabaseException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(url+dbname,username,password);
				
			}
		} catch (ClassNotFoundException e) {
			throw new DatabaseException("Not able to find driver class", e);
		} catch (SQLException e) {
			throw new DatabaseException("Not able to connect database", e);
		}
		return connection;
	}

	public static void releaseResource(PreparedStatement ps) {
		releaseResource(ps, null);
	}

	public static void releaseResource(PreparedStatement ps, ResultSet resultSet) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the dbname
	 */
	public String getDbname() {
		return dbname;
	}

	/**
	 * @param dbname the dbname to set
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();
		if(connection!=null)
			System.out.println("Successful  :)");
		System.out.println(DBConnection.getInstance().getUrl()+DBConnection.getInstance().getDbname()+DBConnection.getInstance().getUsername()+DBConnection.getInstance().getPassword());
	}

}
