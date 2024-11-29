<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pending Requests</title>
</head>
<body>
    <h2>Pending Access Requests</h2>
    <form action="ApprovalServlet" method="post">
        <table border="1">
            <thead>
                <tr>
                    <th>Request ID</th>
                    <th>Employee Name</th>
                    <th>Software Name</th>
                    <th>Access Type</th>
                    <th>Reason</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String dbUrl = "jdbc:postgresql://localhost:5432/uas_db";
                    String dbUser = "postgres";
                    String dbPassword = "password";

                    try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery(
                             "SELECT r.id, u.username, s.name, r.access_type, r.reason " +
                             "FROM requests r " +
                             "JOIN users u ON r.user_id = u.id " +
                             "JOIN software s ON r.software_id = s.id " +
                             "WHERE r.status = 'Pending'")) {
                        while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("id") %></td>
                    <td><%= rs.getString("username") %></td>
                    <td><%= rs.getString("name") %></td>
                    <td><%= rs.getString("access_type") %></td>
                    <td><%= rs.getString("reason") %></td>
                    <td>
                        <button type="submit" name="requestId" value="<%= rs.getInt("id") %>" formaction="ApprovalServlet?action=Approve">Approve</button>
                        <button type="submit" name="requestId" value="<%= rs.getInt("id") %>" formaction="ApprovalServlet?action=Reject">Reject</button>
                    </td>
                </tr>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
            </tbody>
        </table>
    </form>
</body>
</html>
