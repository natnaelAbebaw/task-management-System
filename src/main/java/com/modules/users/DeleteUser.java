package com.modules.users;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        doDelete(request,response);
}

      protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	 int id = Integer.parseInt(request.getPathInfo().substring(1)) ;
	  HttpSession session = request.getSession(false);
	    
      UserModel user = (UserModel) session.getAttribute("user");
      System.out.println(user.getId());
      System.out.println(id);
	 try {
		  if(id == user.getId() ) {
			  throw new Error("You can not delete yourself!");
		  }
		  
		  
		 
		 userDAO.deleteUser(id);
		 request.getRequestDispatcher("/secured/admin/getAllUsers").forward(request, response);
	} catch (Exception e) {
		e.printStackTrace();
	}
}


}
