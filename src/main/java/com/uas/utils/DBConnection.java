package com.uas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/uas_db";  // Change this to your DB URL
    private static final String DB_USER = "postgres";  // Your DB username
    private static final String DB_PASSWORD = "1234";  // Your DB password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");  // Register PostgreSQL JDBC Driver
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }
    }
}