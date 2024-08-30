package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.UserDAO;
import Model.User;

/**
 * Servlet implementation class CustomerServlet
 */

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
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
		String action = request.getParameter("action");
		UserDAO userDAO = new UserDAO();
		HttpSession session = request.getSession();
		
		if("register".equals(action)) {
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRole("customer");
			if(userDAO.registration(user)) {
				session.setAttribute("user", user);
				response.sendRedirect("customerHome.jsp");
			}else {
				response.sendRedirect("error.jsp");
			}
		}else if("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			User user = userDAO.loginUser(email, password);
			if(user != null && "customer".equals(user.getRole())) {
				session.setAttribute("user", user);
				response.sendRedirect("customerHome.jsp");
			}else {
				response.sendRedirect("login.jsp");
			}
		}
		doGet(request, response);
	}

}
