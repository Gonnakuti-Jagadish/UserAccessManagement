<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <h2>Sign Up</h2>
   <form action="SignUpServlet" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="role">Role:</label>
    <select id="role" name="role" required>
        <option value="Admin">Admin</option>
        <option value="Manager">Manager</option>
        <option value="Viewer">Viewer</option>
    </select><br><br>

    <input type="submit" value="Sign Up">
</form>

</body>
</html>
