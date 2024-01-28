package com.modules.tasks;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.modules.users.UserModel;

/**
 * Servlet implementation class searchTasks
 */
public class SearchTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTasks() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    private TaskDAO taskDAO;
    

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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query  = request.getParameter("query");
		 HttpSession session = request.getSession(false);
	        
		 UserModel user = (UserModel) session.getAttribute("user");
		List<TaskModel> tasks = taskDAO.searchTask(query, user.getId());
		
		request.setAttribute("taskList", tasks);
		request.setAttribute("user",user );
		request.setAttribute("searchMode","true" );
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
