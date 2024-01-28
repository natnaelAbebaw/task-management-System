<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.modules.users.UserModel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;400;700;900&family=Moirai+One&display=swap" rel="stylesheet">
    <title>User Form</title>    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
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
            min-height: 100vh;
        }
        
        *{
        padding:0;
        margin: 0;
        box-sizing: border-box;
        }
        

        .box {
            width: 60%;
            margin: 0 auto;
            background-color: #fff;
            padding: 3rem;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-top: 2px solid #e74c3c;
            position: relative;
            height:80vh;
        }
        
        .back{
          position: absolute;
          top: 1rem;
          left:1rem;
          font-size: 1.4rem;
          color:#e74c3c;
        }
        

        h2 {
            color: #e74c3c;
            font: 1.8rem;
            margin-bottom: 1rem;
        }

      
       
      
            h3 {
            color: #e74c3c;
            font: 1.8rem;
            margin-bottom: 0.5rem;
        }
        
       
       .button{
       display:inline-block;
       padding:5px 8px;
       border:none;
       font-size: 1rem;
       font-family: inherit;
       background-color: #EA6658;
       cursor:pointer;
       color:#fff;
       border-radius: 3px;
       text-decoration:none;
       }
       .button:hover{
      background-color: #e74c3c;
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
    
    .users{
     display:flex;
     flex-direction: column;
     gap: 5px;
     overflow: auto;
     padding: 1rem;
     height: 60vh;
    }
       
     .user{
       border-radius: 5px;
       box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
       display:grid;
       grid-template-columns: 1fr 1fr 1fr 1fr;
       justify-items: center;
       padding: 1rem;
       

    }
    
       
    </style>
</head>
<body>
  
    <div class="box">
    <h2>Manage User</h2>
    
    <a class="back" href="/TaskManagementSystem/secured/tasks"><ion-icon name="arrow-back-outline"></ion-icon></a>
  
    <%
   Object ErrorMessage = request.getAttribute("error"); 

    List<UserModel> users = (List<UserModel>)(request.getAttribute("usersList"));
    UserModel currentUser = (UserModel) request.getAttribute("currentUser");
    
    if (ErrorMessage != null) {
%>
   
    <div class="errorMessage">
    <h3>Error occur:</h3>
        <%= ErrorMessage %>
    </div>
<%
    }
%>
    
     <div class="users">
      <% 
      
       if(users != null && !users.isEmpty() ){
	   for (UserModel user : users){
		   %>
          <div class="user">
	   <div><%=user.getUsername() %></div>
	   <div><%=user.getEmail() %></div>
	   <div><%=user.getRole() %></div>
	   <%if(currentUser.getId() != user.getId()){
		   %>
		   <a class="button" href="/TaskManagementSystem/secured/admin/deleteUser/<%= user.getId()%>">Delete</a>
		   <% 
	   }%>

	   </div>
       
<%
	   }
   }
%>
    </div>
</body>
</html>