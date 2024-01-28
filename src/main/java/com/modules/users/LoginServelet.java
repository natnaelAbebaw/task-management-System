package com.modules.users;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Signup
 */
public class LoginServelet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	UserDAO  userDAO;
    
    public LoginServelet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        try {
        	userDAO = new UserDAO();
        } catch (Exception e) {
        	System.out.println(e);
            throw new ServletException("Error establishing database connection", e);
        }
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		 System.out.println(email + password);
		 try {
	       if (email == null || password == null ) {
					throw new Error("Incomplete cridential!");
				}
	       
		 UserModel user = userDAO.login(email, password);
		 
		 HttpSession session = request.getSession();
		 
	    
         session.setAttribute("user", user);
         
         request.getRequestDispatcher("secured/tasks").forward(request, response);
		 }catch(Error e) {
			 request.setAttribute("error", e.getMessage());
			 request.setAttribute("email", email);
			 request.setAttribute("password", password);
			 request.getRequestDispatcher("login.jsp").forward(request,response);
		 }
		 
	}

}
