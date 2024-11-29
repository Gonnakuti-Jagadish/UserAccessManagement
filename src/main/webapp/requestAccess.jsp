<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Request Access</title>
</head>
<body>
    <h2>Request Access to Software</h2>
    <form action="RequestServlet" method="post">
        <label for="softwareId">Select Software:</label>
        <select id="softwareId" name="softwareId" required>
            <%
                String dbUrl = "jdbc:postgresql://localhost:5432/uas_db";
                String dbUser = "postgres";
                String dbPassword = "password";

                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT id, name FROM software")) {
                    while (rs.next()) {
            %>
                <option value="<%= rs.getInt("id") %>"><%= rs.getString("name") %></option>
            <%
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </select><br><br>

        <label for="accessType">Access Type:</label>
        <select id="accessType" name="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select><br><br>

        <label for="reason">Reason for Request:</label>
        <textarea id="reason" name="reason" required></textarea><br><br>

        <input type="submit" value="Request Access">
    </form>
    <%
        String success = request.getParameter("success");
        if ("true".equals(success)) {
    %>
        <p style="color:green;">Request submitted successfully!</p>
    <%
        }
    %>
</body>
</html>
