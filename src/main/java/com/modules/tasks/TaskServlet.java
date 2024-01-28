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


public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private TaskDAO taskDAO;
    
    public TaskServlet() {
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
	    String id = request.getPathInfo();
        try {
        if (id == null || id == "/") {
        	getAllTasks(request,response);
        }
        else {
        	getOneTask(request,response);
        }
        }catch(NullPointerException e) {
            request.setAttribute("error", "An error occurred: " + "No task found by this id: " + id );
            request.getRequestDispatcher("/globalErrorHandler").forward(request, response);
        }catch(Exception e) {
            request.setAttribute("error", "An error occurred: " + "Invalid id: " + id );
            request.getRequestDispatcher("/globalErrorHandler").forward(request, response);
        }
        
	    }
		
	
	private void getAllTasks(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			 HttpSession session = request.getSession(false);
		        
			 UserModel user = (UserModel) session.getAttribute("user");
			List<TaskModel> tasks =  taskDAO.getAllTasks(user.getId());
			
			request.setAttribute("taskList", tasks);
			request.setAttribute("user",user );
			
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (Exception e) {
			 throw new Error(e);
	         }
		}
//	

	

	private void getOneTask(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		try {  
		System.out.println(request.getPathInfo());
	    int id = Integer.parseInt(request.getPathInfo().substring(1)) ;
	    System.out.println(id);
	    HttpSession session = request.getSession(false);
        UserModel user = (UserModel) session.getAttribute("user");
        
		TaskModel task =  taskDAO.getOneTask(id,user.getId());
	
		request.setAttribute("id",task.getId());
		request.setAttribute("title",task.getTitle());
		request.setAttribute("description",task.getDescription() );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("dueDate",dateFormat.format(new Date(task.getDueDate().getTime())) );
		request.setAttribute("priority",task.getPriorty() );
		request.setAttribute("id",id);
		
		request.getRequestDispatcher("/secured/UpdateTask.jsp").forward(request, response);
		
		
		
		} catch (Exception e) {
		
			 throw new Error(e);
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
             
             TaskModel newtask = new TaskModel(title, description,date, priority,user.getId());

             int id = taskDAO.addTask(newtask);
             
             getAllTasks(request,response);
             
         } catch (ParseException e) {
        	 request.setAttribute("error", e.getMessage());
			 request.setAttribute("title", title);
			 request.setAttribute("description", description);
			 request.setAttribute("dueDate", dueDate);
			 request.setAttribute("priority", priority);
			 request.getRequestDispatcher("taskForm.jsp").forward(request,response);
         }
         
         
	}
	
	

}
