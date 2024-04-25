package com.almamater.user.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static final String jdbc = "jdbc:mysql://localhost:3306/alumini_database";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String username, String password) throws ConnectionException {
        try {
            return DriverManager.getConnection(jdbc, username, password);
        } catch (SQLException e) {
            throw new ConnectionException("Failed to establish a database connection", e);
        }
    }
}

