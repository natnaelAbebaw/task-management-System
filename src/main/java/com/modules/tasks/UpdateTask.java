package com.modules.tasks;

import jakarta.servlet.ServletConfig;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.modules.users.UserModel;


public class UpdateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private TaskDAO taskDAO;
    
    public UpdateTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		  // Load the JDBC driver
        try {
        System.out.println("what");
         taskDAO = new TaskDAO();
        } catch (Exception e) {
        	System.out.println(e);
            throw new ServletException("Error establishing database connection", e);
        }
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPut(request,response);
	    }
		
	
	
	  protected void doPut(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		     int id = Integer.parseInt(request.getPathInfo().substring(1)) ;
		     String title = request.getParameter("title");
	         String description = request.getParameter("description");
	         String dueDate = request.getParameter("dueDate");
	         String priority = request.getParameter("priority");
	         
	         
	         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	         HttpSession session = request.getSession(false);
	         UserModel user = (UserModel) session.getAttribute("user");
	         
		  try {  
			  if(title == null || description == null || dueDate == null || priority == null ) {
	        		 throw new Error("Incomplete input!");
	        	 }
	        	 
			    Date date = dateFormat.parse(dueDate);
	             
	            TaskModel newtask = new TaskModel(id,title, description,date, priority,user.getId());
			
				taskDAO.updateTask(newtask);
		
				request.getRequestDispatcher("/secured/tasks").forward(request, response);
				
				} catch (Exception e) {
				
					 throw new Error(e);
					
				}
	    }


}
