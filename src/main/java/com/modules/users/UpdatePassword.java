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
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassword() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String passwordConfirm = request.getParameter("passwordConfirm");
		
		
		HttpSession session = request.getSession(false);
        
		 UserModel user = (UserModel) session.getAttribute("user");
		
		try {
			if (currentPassword == null  || newPassword == null || passwordConfirm == null ) {
				throw new Error("Incomplete cridential!");
			}
			else if (!UserDAO.hashPassword(currentPassword).equals(user.getPassword())) {
				throw new Error("Incorrect password!");
			}
			else if (newPassword.length() < 8) {
				throw new Error("Passwords should be atleast 8 characters!");
			}
			else if (!newPassword.equals(passwordConfirm) ) {
				throw new Error("Passwords do not match!");
			}
			
			
			 userDAO.updatePassword(newPassword,user.getId());
			 
			 user.setPassword(UserDAO.hashPassword(newPassword));
			 session.setAttribute("user", user);
			 
			 request.setAttribute("success", "true");
			 request.getRequestDispatcher("/secured/profile/"+ user.getId()).forward(request, response);
	         }
			catch(Throwable e) {
				 request.setAttribute("error", e.getMessage());
				 request.setAttribute("currentPassword", currentPassword);
				 request.setAttribute("newPassword", newPassword);
				 request.setAttribute("passwordConfirm", passwordConfirm);
				 request.getRequestDispatcher("/secured/profile/"+ user.getId()).forward(request, response);
			}
	}

}
