package com.modules.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.modules.tasks.TaskModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



import jakarta.servlet.ServletException;



public class UserDAO {
    private Connection connection;
	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/tasks";
    private static final String username = "root";
    private static final String password = "1234";

    public UserDAO() throws ServletException {
    	 try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             throw new ServletException("Error loading JDBC driver", e);
         }
         try {
             this.connection = DriverManager.getConnection(jdbcUrl, username, password);
         } catch (SQLException e) {
         	System.out.println(e);
             throw new ServletException("Er UNIQUE,\"ror establishing database connection", e);
         }
      
         createUsersTable();
    }
    
    public static String hashPassword(String plainTextPassword) {
        try {
            // Get an instance of the SHA-256 message digest algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Update the digest with the bytes of the password
            md.update(plainTextPassword.getBytes());

            // Get the hash bytes
            byte[] hashBytes = md.digest();

            // Convert the hash bytes to a Base64-encoded string
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
    
    
    private void createUsersTable() {
    	 String createUserTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                 + "id INT AUTO_INCREMENT PRIMARY KEY,"
                 + "username VARCHAR(255) NOT NULL,"
                 + "email VARCHAR(255) NOT NULL UNIQUE,"
                 + "password VARCHAR(255) NOT NULL,"
                 + "role VARCHAR(255) DEFAULT 'user'"
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
    

    public List<UserModel> getAllUsers() {
    	List<UserModel> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");

                UserModel user =new UserModel(id,username, email, role);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    

    private UserModel getUserById(int id) {
    
    	String sqlQuery = "SELECT * FROM users WHERE id = ?";
    	
    	try(PreparedStatement statement = connection.prepareStatement(sqlQuery)){
    		statement.setInt(1,id); 
    		ResultSet resultSet = statement.executeQuery();
    		
    		if(resultSet.next()) {
    			
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");

    			UserModel user  = new UserModel(id, username,email,role);
    			return user;
    		}
    		
    		
    	}
    	catch(SQLException e) {
    		  e.printStackTrace();
    	}
		return null;
    	
    	
    }

    
    private UserModel getUserByEmail(String email) {
        
    	String sqlQuery = "SELECT * FROM users WHERE email = ?";
    	
    	try(PreparedStatement statement = connection.prepareStatement(sqlQuery)){
    		statement.setString(1,email); 
    		ResultSet resultSet = statement.executeQuery();
    		
    		if(resultSet.next()) {
    			int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

    			UserModel user  = new UserModel(id, username,email,password,role);
    			return user;
    		}
    		
    		
    	}
    	catch(SQLException e) {
    		  e.printStackTrace();
    	}
		return null;
    	
    	
    }

  

    UserModel signup(String username,String email,String password ) {
         String hashPassword = hashPassword(password);
         
        System.out.println(hashPassword);
         String sqlQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
         
         try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
         	preparedStatement.setString(1, username);
         	preparedStatement.setString(2, email);
         	preparedStatement.setString(3, hashPassword);
         	preparedStatement.executeUpdate();
         	
         	 ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
             int userId = -1;
             if (generatedKeys.next()) {
            	 userId = generatedKeys.getInt(1);
             }
             
            UserModel user = getUserById(userId);
         	return user;
         
          } catch (SQLException e) {
           e.printStackTrace();
        	 throw new Error("Email aready used!");
    }
         
    }
    
    UserModel login(String email,String password ) {
    	  UserModel user = getUserByEmail(email);
    	  if(user == null) {
    		  throw new Error("User not found!");
    	  }
    	  System.out.println("pass" +hashPassword(password) + user.getPassword() );
    	  boolean isCorrectPassword = hashPassword(password).equals(user.getPassword());
    	  if(!isCorrectPassword) {
    		  throw new Error("Incorrect password!");
    	  }
    	  
    	 return user;
   }
    
    
    public void updatePassword(String password, int id) {
    	 String hashPassword = hashPassword(password);
        String updateQuery = "UPDATE users SET password = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
        	preparedStatement.setString(1,hashPassword );
        	preparedStatement.setInt(2, id);
        	
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
    
    public void updateProfile(String username, String email,int id) {
  
       String updateQuery = "UPDATE users SET username = ?, email = ? WHERE id = ?";
       try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
       	preparedStatement.setString(1,username );
       	preparedStatement.setString(2, email);
       	preparedStatement.setInt(3, id);
       	
       	int rowsUpdated = preparedStatement.executeUpdate();

             if (rowsUpdated > 0) {
                 System.out.println("Row updated successfully.");
             } else {
                 System.out.println("No rows were updated.");
             }

       } catch (SQLException e) {
           e.printStackTrace();
           throw new Error("Email aready used!");
       }
   }
    
    public void deleteUser(int id) {
        String deleteQuery = "DELETE FROM users WHERE id = ?";
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
    
}