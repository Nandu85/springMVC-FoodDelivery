package com.narola.fooddelivery.utility;

import com.narola.fooddelivery.exception.DatabaseException;

import java.sql.*;


public class DBConnection {

    private static DBConnection dbConnection = null;

    private static Connection connection = null;

    private static String dbname;

    private static String url;

    private static String username;

    private static String password;

    public DBConnection() {
    }

    public DBConnection(String dbname, String url, String username, String password) {
        this.dbname = dbname;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
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

    public Connection getConnection() throws DatabaseException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url + dbname, username, password);

            }
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Not able to find driver class", e);
        } catch (SQLException e) {
            throw new DatabaseException("Not able to connect database", e);
        }
        return connection;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
