<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="Model.Slot" %>
<%@ page import="DAO.SlotDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Home</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">
        <h1>Welcome, ${sessionScope.user.name}</h1>
        <h2>Available Slots</h2>
        <form action="BookingSlot.java" method="post">
            <select name="slotId">
                <%
                    SlotDAO slotDAO = new SlotDAO();
                    List<Slot> slots = slotDAO.getAvailableSlots();
                    for (Slot slot : slots) {
                %>
                <option value="<%= slot.getId() %>"><%= slot.getNumber() %></option>
                <%
                    }
                %>
            </select>
            <label for="startTime">Start Time:</label>
            <input type="datetime-local" name="startTime" required>
            <label for="endTime">End Time:</label>
            <input type="datetime-local" name="endTime" required>
            <input type="submit" value="Book Slot">
        </form>
        <a href="history.jsp">View Booking History</a>
        <a href="logout.jsp">Logout</a>
    </div>
</body>
</html>