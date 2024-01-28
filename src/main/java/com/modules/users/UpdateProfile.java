package com.modules.users;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class userServelet
 */
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   UserDAO  userDAO;
	   
	   
	    public void init(ServletConfig config) throws ServletException {
	        try {
	        	userDAO = new UserDAO();
	        } catch (Exception e) {
	        	System.out.println(e);
	            throw new ServletException("Error establishing database connection", e);
	        }
		}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPut(request,response);
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String userName = request.getParameter("username");
	String email = request.getParameter("email");

	HttpSession session = request.getSession(false);
	UserModel user = (UserModel) session.getAttribute("user");
	try {
		if (userName == null  || email == null  ) {
			throw new Error("Incomplete cridential!");
		}

		 userDAO.updateProfile(userName,email,user.getId());
		 user.setUsername(userName);
		 user.setEmail(email);
		 session.setAttribute("user", user);
		 request.setAttribute("success", "true");
		 request.getRequestDispatcher("/secured/profile/"+ user.getId()).forward(request, response);
         }
		catch(Throwable e) {
			 request.setAttribute("error", e.getMessage());
			 request.setAttribute("userName", userName);
			 request.setAttribute("email", email);
			 request.getRequestDispatcher("/secured/profile/"+ user.getId()).forward(request, response);
		}
}

}
