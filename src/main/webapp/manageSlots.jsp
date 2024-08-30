<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Slot" %>
<%@ page import="DAO.SlotDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Slots</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">
        <h1>Manage Slots</h1>
        
        <form action="AdminServlet" method="post">
	        <label for="slotNumber">Slot Number:</label>
	        <input type="text" id="slotNumber" name="slotNumber" required>
	        <input type="hidden" name="action" value="addSlot"> <!-- Hidden field to specify the action -->
	        <button type="submit">Add Slot</button>
    	</form>
        
	        <h3>Existing Slots</h3>
			    <table>
			        <thead>
			            <tr>
			                <th>Slot ID</th>
			                <th>Slot Number</th>
			                <th>Status</th>
			                <th>Actions</th>
			            </tr>
			        </thead>
			        <tbody>
			            <!-- Populate this with slots from the database -->
			            <!-- Example row:
			            <tr>
			                <td>1</td>
			                <td>A1</td>
			                <td>available</td>
			                <td><a href="AdminServlet?action=deleteSlot&id=1">Delete</a></td>
			            </tr>
			            -->
			        </tbody>
	    	</table>
        
        
        <form action="admin" method="post">
            <input type="hidden" name="action" value="deleteSlot">
            <label for="slotId">Select Slot to Delete:</label>
            <select name="slotId" id="slotId">
                <%
                    SlotDAO slotDAO = new SlotDAO();
                    List<Slot> slots = slotDAO.getAvailableSlots();
                    for (Slot slot : slots) {
                %>
                <option value="<%= slot.getId() %>"><%= slot.getNumber() %> - <%= slot.getStatus() %></option>
                <%
                    }
                %>
            </select>
            <input type="submit" value="Delete Slot">
        </form>
        <a href="adminHome.jsp">Back to Dashboard</a>
        <%
            if ("true".equals(request.getParameter("success"))) {
                out.println("<div class='success-message'>Slot deleted successfully!</div>");
            } else if ("true".equals(request.getParameter("error"))) {
                out.println("<div class='error-message'>Error deleting slot. Please try again.</div>");
            }
        %>
    </div>
</body>
</html>