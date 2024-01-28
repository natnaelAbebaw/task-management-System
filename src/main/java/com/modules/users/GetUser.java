package com.modules.users;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.modules.tasks.TaskModel;

/**
 * Servlet implementation class userServelet
 */
public class GetUser extends HttpServlet {
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
    public GetUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	    try {  
		
		    int id = Integer.parseInt(request.getPathInfo().substring(1)) ;
		  
		    HttpSession session = request.getSession(false);
		    
	        UserModel user = (UserModel) session.getAttribute("user");
	        
			request.setAttribute("id",user.getId());
			request.setAttribute("username",user.getUsername());
			request.setAttribute("email",user.getEmail() );
			
			request.getRequestDispatcher("/secured/updateProfile.jsp").forward(request, response);
			
			
			
			} catch (Exception e) {
			
				 throw new Error(e);
				
			}
}



}
