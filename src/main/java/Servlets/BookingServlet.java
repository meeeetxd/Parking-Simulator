package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.BookingDAO;
import Model.Booking;
import Model.User;
import Utility.EmailService;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null && "customer".equals(user.getRole())) {
			int slotId = Integer.parseInt(request.getParameter("slotId"));
			String startTime = request.getParameter("StartTime");
			String endTime = request.getParameter("endTime");
			
			Booking booking = new Booking();
			booking.setUserId(user.getId());
			booking.setSlotId(slotId);
			booking.setStartTime(startTime);
			booking.setEndtime(endTime);
			booking.setStatus("active");
			booking.setPaymentStatus("unpaid");
			
			BookingDAO bookingDAO = new BookingDAO();
			if(bookingDAO.bookSlot(booking)) {
				EmailService.sendConfirmation(user.getEmail(),booking);
				response.sendRedirect("confirmation.jsp");
			}else {
				response.sendRedirect("error.jsp");
				
			}
		}else {
			response.sendRedirect("login.jsp");
		}
		doGet(request, response);
	}

}
