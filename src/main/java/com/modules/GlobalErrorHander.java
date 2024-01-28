package com.modules;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class GlobalErrorHander
 */
public class GlobalErrorHander extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GlobalErrorHander() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  
//		         int statusCode = response.getStatus();
//		         String errorMessage = (String) request.getAttribute("javax.servlet.error.message");

		        // Forward to an error page or send a custom error response
		        request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
  
	
}
