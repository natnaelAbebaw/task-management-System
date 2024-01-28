package com.modules.users;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.modules.tasks.TaskModel;

/**
 * Servlet implementation class GetAllUser
 */
public class GetAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUser() {
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
		HttpSession session = request.getSession(false);
	    
	      UserModel user = (UserModel) session.getAttribute("user");
		
		try {
			
			 
			List<UserModel> users =  userDAO.getAllUsers();
			
			request.setAttribute("usersList", users);
		
			request.setAttribute("currentUser", user);
			
			request.getRequestDispatcher("usersList.jsp").forward(request, response);
		} catch (Exception e) {
			 throw new Error(e);
	         }
		}
	

}
