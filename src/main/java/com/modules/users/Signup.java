package com.modules.users;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

import com.modules.tasks.TaskDAO;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO  userDAO;
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
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
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		
		
		try {
		if (username == null || email == null || password == null || passwordConfirm == null ) {
			throw new Error("Incomplete cridential!");
		}
		if (password.length() < 8) {
			throw new Error("Passwords should be atleast 8 characters!");
		}
		if (!password.equals(passwordConfirm) ) {
			throw new Error("Passwords do not match!");
		}
			
		 UserModel user = userDAO.signup(username, email, password);
		 HttpSession session = request.getSession();
         session.setAttribute("user", user);
         request.getRequestDispatcher("/secured/tasks").forward(request, response);
         
         }
		catch(Throwable e) {
			 request.setAttribute("error", e.getMessage());
			 request.setAttribute("username", username);
			 request.setAttribute("email", email);
			 request.setAttribute("password", password);
			 request.setAttribute("passwordConfirm", passwordConfirm);
			 request.getRequestDispatcher("signup.jsp").forward(request,response);
		}

	}

};
