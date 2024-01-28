package com.modules.tasks;
import java.util.Date;

enum Status {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED,
    DEFERRED
}

enum Priority {
    HIGH("High Priority"),
    MEDIUM("Medium Priority"),
    LOW("Low Priority");

    private final String label;

    Priority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

public class TaskModel {
    private int id = -1;
    private String title;
    private String description;
    private Date dueDate;
    private String priority;
    private int userId;

    public TaskModel(String title, String description, Date dueDate,String priority,int userId) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.userId = userId;
    }
    
    public TaskModel(int id,String title, String description, Date dueDate,String priority,int userId) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.userId = userId;
    }
    
    public String getTitle() {
    	return title;
    }
    public String getDescription() {
    	return description;
    }
    public Date getDueDate() {
    	return dueDate;
    }
    public String  getPriorty() {
    	return priority;
    }
    
    public int getUserId() {
    	return userId;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
  
}