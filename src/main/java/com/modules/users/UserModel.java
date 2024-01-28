
package com.modules.users;


public class UserModel {
    private int id = -1;
    private String username;
    private String email;
    private String password;
    private String role;
    
    public UserModel(int id,String username, String email,String password,String role) {
    	this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }
    
    public UserModel(int id,String username, String email,String role) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.role = role;
    }
    
 
    public String getUsername() {
    	return username;
    }
    public String getEmail() {
    	return email;
    }
    
    public String getRole() {
    	return role;
    }
    public int getId() {
    	return id;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
   

}