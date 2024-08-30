<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">
        <h1>Login</h1>
        <form action="CustomerServlet" method="post">
            <input type="hidden" name="action" value="login">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>
            <input type="submit" value="Login">
        </form>
        <a href="register.jsp">Register</a>
        <a href="adminLogin.jsp">Admin Login</a>
    </div>
</body>
</html>