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

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int softwareId = Integer.parseInt(request.getParameter("softwareId"));
        String accessType = request.getParameter("accessType");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO requests (user_id, software_id, access_type) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, softwareId);
                stmt.setString(3, accessType);
                int rows = stmt.executeUpdate();
                
                if (rows > 0) {
                    response.getWriter().write("Request submitted successfully.");
                } else {
                    response.getWriter().write("Error submitting request.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database connection error: " + e.getMessage());
        }
    }
}
