<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">
        <h1>Register</h1>
        <form action="CustomerServlet" method="post">
            <input type="hidden" name="action" value="register">
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" required>
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required>
            <input type="submit" value="Register">
        </form>
        <a href="login.jsp">Back to Login</a>
    </div>
</body>
</html>