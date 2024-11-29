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

@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String softwareName = request.getParameter("softwareName");
        String softwareDescription = request.getParameter("softwareDescription");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO software (name, description) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, softwareName);
                stmt.setString(2, softwareDescription);
                int rows = stmt.executeUpdate();
                
                if (rows > 0) {
                    response.getWriter().write("Software added successfully.");
                } else {
                    response.getWriter().write("Error adding software.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database connection error: " + e.getMessage());
        }
    }
}
