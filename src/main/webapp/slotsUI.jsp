<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="DAO.SlotDAO" %>
<%@ page import="Model.Slot" %>
<html>
<head>
<meta charset="UTF-8">
<title>Parking Slot Selector</title>
<link rel="stylesheet" href="css/Slotstyles.css">
</head>
<body>
	<h1>Parking Slot Selector</h1>
    <div class="parking-lot">
        <%
            // Fetch slots from the database using SlotDAO
            DAO.SlotDAO slotDAO = new DAO.SlotDAO();
            List<Model.Slot> slots = slotDAO.getAllSlots();
       
            for (Model.Slot slot : slots) {
                String slotClass = slot.getStatus().equals("booked") ? "booked" : "available";
        %>
                <div class="slot <%= slotClass %>" data-id="<%= slot.getId() %>" data-number="<%= slot.getNumber() %>">
                    <div class="car-shape"></div>
                </div>
        <%
            }
        %>
    </div>
    <button id="bookSlotBtn">Book Selected Slot</button>
   <a href="customerHome.jsp">Home</a>

    <script src="script.js"></script>
</body>
</html>