<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Log In">
    </form>
    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <p style="color:red;">Invalid username or password. Please try again.</p>
    <%
        }
    %>
</body>
</html>
