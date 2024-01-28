<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.modules.tasks.TaskModel" %>
<%@ page import="com.modules.users.UserModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Ops+One&family=Lato:wght@100;400;700;900&display=swap" rel="stylesheet">
    <title>Task Management</title>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #555;
            font-family: 'Lato', sans-serif;
     
            padding: 50px;
            display:grid;
            place-items:center;
            min-height: 110vh;
        }
        
        *{
        padding:0;
        margin: 0;
        box-sizing: border-box;
        }
        

        .dashboard {
            width: 80%;
            margin: 0 auto;
            display:grid;
            grid-template-columns: 1fr 3fr;
            background-color: #fff;
            min-height:80vh;
            border-radius: 1rem;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            overflow:hidden;
        }
        
        .sidebar{
          background-color: #e74c3c;
          display:flex;
          flex-direction: column;
          padding: 2rem;
        }
        
        
        .logo{
        font-family: 'Black Ops One', system-ui;
        font-size: 2.4rem;
        color:#fff;
        text-align:center;
        margin-bottom: 1rem;
        }
        
        .tabs{
         display:flex;
         flex-direction: column;
         min-height:75vh;
         gap:2px;
         padding: 1rem;
        }
        
        .tabs a{ 
          font-size: 18px;
          text-transform: capitalize;
          display:flex;
          gap:7px;
          cursor:pointer;
          padding:5px 8px; 
          align-items:center;
          border-radius: 5px;
          
        }
        .tabs a:hover{ 
         background-color: #E53C29;
        }
        
        .add-Task{ 
         background-color: #fff;
         padding:8px 1rem;
         color:#e74c3c;
         border-radius: 7px;
         text-align:center;
         margin-bottom: 1rem;
         cursor:pointer;
        }
        
         .add-Task:hover{
         background-color: #eee;
         }
         
       
        .tabs a:last-child{ 
        margin-top:auto;
        }
        
        .tabs a{
          color:#fff;
        }
        .topbar{
        display:flex;
        justify-content:space-between;
        padding:0.8rem 2rem;
        align-items:center;
        border-bottom: 1px solid #F6BFB9;
        }
        
        
        .profile {
          display:flex;
          align-items:center;
        
        }
        
        
        .profile ion-icon{
         font-size: 2rem;
         color:#e74c3c;
         margin-right:5px;
        }
        
         .profile span{
        font-size: 1rem;
         color:#e74c3c;
         transform:translateY(-2px);
        }
        
        .tasks{
            display:grid;
            grid-template-columns: 1fr 1fr 1fr;
            column-gap: 1rem;
            row-gap: 1rem;
            padding: 1rem;
            overflow-y: auto;
            height:90vh;
        }
        
        a{
        text-decoration: none;
        }
        
       .task{
       padding: 1rem 1rem 0.8rem 1rem;
       box-shadow: 0 2px 15px rgba(246, 191, 185, 0.6);
       background-color:#FFFBFA;
       border: 1px solid #F6BFB9;
       border-radius:5px;
       height: 15rem;
       position:relative;
       display:flex;
       width: 14rem;
       flex-direction:column;
       }
        
        .task .title{
         border-bottom: 1px solid #F6BFB9;
         white-space: nowrap;        
         overflow: hidden;           
        overflow:auto;   
        max-width: 200px;    
        text-transform: capitalize;
      scrollbar-width: thin;
      -ms-overflow-style: none; 
      font-size:1.4rem; 
      font-weight: 500;
      color:#e74c3c;
      padding: 5px 0;
        }
        .task p{
         padding:1rem 0;
         height: 9rem;
         overflow:auto; 
        }
        

/* Hide the scrollbar for WebKit-based browsers (Chrome, Safari) */
.task .title::-webkit-scrollbar {
  display: none;
}
        
        .butt{
          display:flex;
          font-size: 0.8rem;
          justify-content: space-between;
          align-items:center;
          margin-top:auto;
        }
        
        .proirity{
           padding: 3px 5px;
           background-color: #F6BFB9;
           border-radius: 2px;
        }
        
        .dropdown{
        position:absolute;
        top:5px;
        right:5px;
        }
        
       .list {
        display:none;
        width: 5rem;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        position:absolute;
        top:1rem;
        right:-3rem;
        z-index: 10;
        padding: 5px;
        background-color:#fff;
      
        }
        
        
        
        .list a{
          padding: 2px;
          display:flex;
          align-items: center;
          text-decoration: none;
          font-size:1rem;
            color:#e74c3c;
        }
        
        .list a:hover{
          background-color:#eee;
          cursor:pointer;
        }
        
        .drop{
         cursor:pointer;
        }
        
        .list:hover{
          display:flex;
         flex-direction: column;
        }
        
        .drop:hover + .list{
         display:flex;
         flex-direction: column;
        }
        
        h2 {
            color: #e74c3c;
            font: 1.8rem;
            
        }
        
          h3 {
            color: #e74c3c;
            font: 1.8rem;
            margin-bottom: 0.5rem;
        }
       
       form{
       position:relative;
       width:40%;
       } 

       
       input{
         width:100%;
         padding: 8px 1rem;
         border: 1px solid #F1998F;
         border-radius: 8px;
       font-family: inherit;
       }
       
       input:focus{
         font-family: inherit;
         outline:1px solid #e74c3c;
       
       }
       
     
          input[id="password"]{
         margin-bottom: 2rem;
       }
       
       button{
       position:absolute;
       top:50%;
       right:7px;
       transform: translateY(-50%);
       border:none;
       font-size: 1.2rem;
       font-family: inherit;
       font-weight: 700;
       background: none;
       cursor:pointer;
       color:#e74c3c;
       border-radius: 3px;
       }
       
       
       
       button:hover{
        transform:translateY(-50%) scale(0.95);
       }
       
     
    
    .errorMessage{
      position: absolute;
      top: 5px;
      left:50%;
      width: 50%;
      transform: translateX(-50%);
      background-color: #fff;
      padding: 1rem;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    
    .searchBar{
       border-radius: 5px;
       box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
       display:flex;
       justify-content:space-between;
       padding: 1rem;
       height: 3rem;
       margin: 1rem;
    }
     .searchBar a{
      font-size:1.2rem;
      color:#e74c3c;
    }
    
       
    </style>
    
</head>



<body>
 <% UserModel user = (UserModel)(request.getAttribute("user")); %>

    <div class="dashboard">
       <div class="sidebar">
         <div class="logo">Tasker</div>
          <a href="/TaskManagementSystem/secured/taskForm.jsp" class="add-Task"> Add tasks</a>
         <div class="tabs">
         <a href="/TaskManagementSystem/secured/profile/${user.id}"><ion-icon name="person-outline"></ion-icon><span>Edit profile</span></a>
         <%
         if (user.getRole().equals("admin")){
        	%>
        	<a href="/TaskManagementSystem/secured/admin/getAllUsers"><ion-icon name="construct-outline"></ion-icon><span>Manage users</span></a>
        	<% 
         }
         %>
         <a href="/TaskManagementSystem/logout"><ion-icon name="log-out-outline"></ion-icon><span>logout</span></a>
         </div>
       </div>
       
       <main>
       <div class="topbar">
        
        <form action="/TaskManagementSystem/secured/searchTasks" method="Get">
        <input placeholder="Search tasks..." name="query"/>
        <button><ion-icon name="search-outline"></ion-icon></button>
        </form>
       
       <div class="profile">
        <ion-icon name="person-circle-outline"></ion-icon>
        <span><%= user.getUsername() %></span>
       </div>
       </div>
     <%  List<TaskModel> tasks = (List<TaskModel>)(request.getAttribute("taskList"));
             String searchMode =  (String)(request.getAttribute("searchMode"));
       
   if (searchMode != null){
	   %>
	   <div class="searchBar">
	   <p><%= tasks.size() %> of search results. </p>
	   <a  href="/TaskManagementSystem/secured/tasks"><ion-icon name="close-outline"></ion-icon></a>
	   </div>
	 <% 
   }
   %>
   
       <div class="tasks">
       
       <%
      
   if(tasks != null && !tasks.isEmpty() ){
	   for (TaskModel task : tasks){
		   %>
           <div class="task">
       <h4 class="title"><%= task.getTitle() %></h4>
        <p> <%= task.getDescription() %> </p>
        <div class="dropdown"> 
        
        <div  class="drop">
        <ion-icon name="ellipsis-vertical"></ion-icon>
        </div>
        
         <div class="list">
          <a href=<%="/TaskManagementSystem/secured/tasks/"+task.getId() %>><ion-icon name="create"></ion-icon><span>Edit</span> </a>
         <a href=<%="/TaskManagementSystem/secured/deleteTask/"+task.getId() %>><ion-icon name="trash"></ion-icon><span>Delete</span></a>
         </div>
       </div>
       
        <div class="butt">
         <div class="date"><%= task.getDueDate() %></div>
        <div class="proirity"><%= task.getPriorty() %></div>
       </div>
       
       </div>
<%
	   }
   }
%>
         
       
       </main>
    </div>
</body>
</html>