package com.modules.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;



public class TaskDAO {
    private Connection connection;
	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/tasks";
    private static final String username = "root";
    private static final String password = "1234";

    public TaskDAO() throws ServletException {
    	
    	  try {
              Class.forName("com.mysql.cj.jdbc.Driver");
          } catch (ClassNotFoundException e) {
              throw new ServletException("Error loading JDBC driver", e);
          }
          try {
               this.connection = DriverManager.getConnection(jdbcUrl, username, password);
            
          } catch (SQLException e) {
          	System.out.println(e);
              throw new ServletException("Error establishing database connection", e);
          }
         System.out.println("is prointed");
        createTasksTable();
    
    }
    
    private void createTasksTable() {
    	String createUserTableSQL = "CREATE TABLE IF NOT EXISTS tasks ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "title VARCHAR(255) NOT NULL,"
                + "description TEXT,"
                + "dueDate DATE,"
                + "priority VARCHAR(255),"
                + "userId int"
                + ")";
        
        try (
              Statement statement = connection.createStatement()
            ) {
             
                statement.executeUpdate(createUserTableSQL);
                System.out.println("Table 'users' created successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    

    public List<TaskModel> getAllTasks(int userId) {
    	List<TaskModel> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE userId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date dueDate = resultSet.getDate("dueDate");
                String priority  = resultSet.getString("priority");
                TaskModel task = new TaskModel(id,title, description, dueDate, priority,userId);
                tasks.add(task);
            } 

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
    
    public TaskModel getOneTask(int id, int userId) {

    	String sqlQuery = "SELECT * FROM tasks WHERE id = ? AND userId = ?";
    	
    	try(PreparedStatement statement = connection.prepareStatement(sqlQuery)){
    		statement.setInt(1,id); 
    		statement.setInt(2, userId);
    		
    		ResultSet resultSet = statement.executeQuery();
    		
    		if(resultSet.next()) {
    			String title = resultSet.getString("title");
    			String description = resultSet.getString("description");
    			Date dueDate = resultSet.getDate("dueDate");
    			String priority  = resultSet.getString("priority");
               
    			TaskModel task = new TaskModel(id, title, description,dueDate,priority,userId);
    			return task;
    		}
    		
    		
    	}
    	catch(SQLException e) {
    		  e.printStackTrace();
    	}
		return null;
    	
    	
    }

    public int addTask(TaskModel task) {
        String sqlQuery = "INSERT INTO tasks (title, description, dueDate, priority, userId) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
        	preparedStatement.setString(1, task.getTitle());
        	preparedStatement.setString(2, task.getDescription());
        	preparedStatement.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
        	preparedStatement.setString(4, task.getPriorty());
        	preparedStatement.setInt(5, task.getUserId());
        	
        	preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int taskId = -1;
            if (generatedKeys.next()) {
                taskId = generatedKeys.getInt(1);
            }
            
            return taskId;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e.getMessage()) ;
        }
        
      
    }
    
    public void updateTask(TaskModel task) {
        String updateQuery = "UPDATE tasks SET title = ?, description = ?, dueDate = ?, priority = ?, userId = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
        	preparedStatement.setString(1, task.getTitle());
        	preparedStatement.setString(2, task.getDescription());
        	preparedStatement.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
        	preparedStatement.setString(4, task.getPriorty());
        	preparedStatement.setInt(5, task.getUserId());
        	preparedStatement.setInt(6, task.getId());
        	
        	int rowsUpdated = preparedStatement.executeUpdate();

              if (rowsUpdated > 0) {
                  System.out.println("Row updated successfully.");
              } else {
                  System.out.println("No rows were updated.");
              }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e.getMessage()) ;
        }
        
       
    }
  

    public void deleteTask(int id) {
        String deleteQuery = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
      
        	preparedStatement.setInt(1, id);
        	
        	int rowsUpdated = preparedStatement.executeUpdate();

              if (rowsUpdated > 0) {
                  System.out.println("Row updated successfully.");
              } else {
                  System.out.println("No rows were updated.");
              }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e.getMessage()) ;
        }
        
    }
  
    public List<TaskModel> searchTask(String query,int userId) {
        String searchQuery = "SELECT * FROM tasks WHERE userId = ? AND title LIKE ? OR description LIKE ?";
        List<TaskModel> tasks = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(searchQuery)) {
        	statement.setInt(1, userId);
        	statement.setString(2,"%" + query + "%");
        	statement.setString(3,"%" + query + "%");
            ResultSet resultSet = statement.executeQuery();
            
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date dueDate = resultSet.getDate("dueDate");
                String priority  = resultSet.getString("priority");
                TaskModel task = new TaskModel(id,title, description, dueDate, priority,userId);
                tasks.add(task);
            } 

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
        
    }
  
}