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


public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private TaskDAO taskDAO;
    
    public DeleteTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		  // Load the JDBC driver
        try {
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
		
		        doDelete(request,response);
	    }
		
	    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	 int id = Integer.parseInt(request.getPathInfo().substring(1)) ;
	    	 try {
	    		 taskDAO.deleteTask(id);
	    		 request.getRequestDispatcher("/secured/tasks").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

}
