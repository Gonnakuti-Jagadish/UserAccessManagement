package com.uas.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uas.utils.DBConnection;


public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/plain");

        // Get user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Validate input
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty() || role == null || role.trim().isEmpty()) {
            response.getWriter().write("Username, password, and role are required.");
            return;
        }

        // Validate role against allowed values
        if (!isValidRole(role)) {
            response.getWriter().write("Invalid role. Allowed roles are: Admin, Manager, Viewer.");
            return;
        }

        // Database connection and insertion
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, role);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    response.getWriter().write("User registered successfully.");
                } else {
                    response.getWriter().write("Error during registration.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred during registration. Please try again later.");
        }
    }

    // Helper method to validate role
    private boolean isValidRole(String role) {
        return role.equals("Admin") || role.equals("Manager") || role.equals("Viewer");
    }
}
