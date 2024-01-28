<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;400;700;900&family=Moirai+One&display=swap" rel="stylesheet">
<title>profile</title>
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
            min-height: 140vh;
        }
        
        *{
        padding:0;
        margin: 0;
        box-sizing: border-box;
        }
        

        .box {
            width: 50%;
            margin: 0 auto;
            background-color: #fff;
            padding: 3rem 5rem;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-top: 2px solid #e74c3c;
            position:relative;
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
            margin-bottom: 2rem;
        }
        
          h3 {
            color: #e74c3c;
            font: 1.8rem;
            margin-bottom: 0.5rem;
        }
        

       label{
        font-size: 1rem;
        margin-bottom: 5px;
        display:inline-block;
       }
       
       input{         width:100%;
         padding: 8px 1rem;
         border: 1px solid #F1998F;
         border-radius: 3px;
         margin-bottom: 1rem;
       font-family: inherit;
       }
       
       input:focus{
         font-family: inherit;
         outline:1px solid #e74c3c;
       
       }
       
     
          input[id="passwordConfirm"]{
         margin-bottom: 2rem;
       }
       
       button{
       padding: 8px 1rem;
       border:none;
       font-size: 1.2rem;
       font-family: inherit;
       font-weight: 700;
       background-color: #EA6658;
       width: 100%;
       cursor:pointer;
       color:#fff;
       border-radius: 3px;
       margin-bottom: 2rem;
       }
       button:hover{
      background-color: #e74c3c;
       }
       
       a{
       color: #e74c3c;
       }
    
    .errorMessage{
      position: fixed;
      top: 5px;
      left:50%;
      width: 50%;
      transform: translateX(-50%);
      background-color: #fff;
      padding: 1rem;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    
    .overlay{
      position:absolute;
      top:0;
      left:0;
      width:100%;
      height:130vh;
      backGround-color: #000;
      opacity:0.4;
      z-index: 20:
    }
    
    .model{
      position:absolute;
      top:50%;
      left:50%;
      width:40%;
      transform:translate(-50%,-50%);
      padding: 2rem 3rem;
      background-color: #fff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      z-index: 30;
      border-radius: 8px;
    }
    
    .model h3{
     color: #e74c3c;
     font-size:1.6rem;
     margin-bottom:3rem;
    }
     .model div{
     display: flex;
     gap: 1rem;
     justify-content: flex-end;
    }
    
     .model div a{
     text-decoration: none;
     font-size:1rem;
     padding: 8px 10px;
     background-color:#e74c3c;
     border-radius:5px;
     color:#fff;
    }
    
     .model div a{
     background-color:#E53C29;
    }
    
    </style>
</head>
<body>


<%
   Object ErrorMessage = request.getAttribute("error"); 
   String username =(String) request.getAttribute("username"); 
   String email =(String)  request.getAttribute("email"); 
   String passwordConfirm =(String)  request.getAttribute("passwordConfirm"); 
   String newPassword =(String)  request.getAttribute("newPassword"); 
   String currentPassword =(String)  request.getAttribute("currentPassword"); 
   String success = (String) request.getAttribute("success"); 
   
   int id =(int)  request.getAttribute("id"); 
   
    if (ErrorMessage != null) {
%>
   
    <div class="errorMessage">
    <h3>Error occur:</h3>
        <%= ErrorMessage %>
    </div>
<%
    }
    
    if (success != null) {
%><div class="overlay"></div>
       <div class="model">
    <h3>Operation sucessfully completed!</h3>
    <div>
         <a href="/TaskManagementSystem/secured/profile/${id}">cancel</a>
         <a href="/TaskManagementSystem/secured/tasks">Go Tasks</a>
    </div>
    </div>
<%
    }
%>
    






<div class="box">
    <h2>Change your profile</h2>
        
    <a class="back" href="/TaskManagementSystem/secured/tasks"><ion-icon name="arrow-back-outline"></ion-icon></a>
    <form action="/TaskManagementSystem/secured/updateProfile/${id}" method="get">
        <label for="username">Username</label> 
        <%
    if (username != null) {
%> 
    <input type="text" id="username" value= <%= username%> name="username" required><br>
<%
    }else{
    	%> 
        <input type="text" id="username" name="username" required><br>
    <%
    }
%>
   

        <label for="email">Email</label>

                <%
    if (email != null) {
%> 
        <input type="email" id="email" value=<%=email  %> name="email" required><br>
<%
    }else{
    	%> 
        <input type="email" id="email"  name="email" required><br>

    <%
    }
%>

 <button type="submit">Update Profile</button>
    </form>
    
      <form action="/TaskManagementSystem/secured/updatePassword/${id}" method="get">
      

        <label for="currentPassword">Current password</label>    
                       <%
    if (currentPassword != null) {
%> 
        <input type="password" id="currentPassword" value=<%=currentPassword%> name="currentPassword" required><br>
<%
    }else{
    	%> 
         <input type="password" id="currentPassword" name="currentPassword" required><br>
    <%
    }
%>
        <label for="newPassword">New password</label>    
                       <%
    if (newPassword != null) {
%> 
        <input type="password" id="newPassword" value=<%=newPassword%> name="newPassword" required><br>
<%
    }else{
    	%> 
         <input type="password" id="newPassword" name="newPassword" required><br>
    <%
    }
%>
        <label for="passwordConfirm">Confirm password</label>        
                       <%
    if (passwordConfirm != null) {
%>                                                                               
        <input type="password" id="passwordConfirm" value=<%=passwordConfirm %> name="passwordConfirm" required><br>
<%
    }else{
    	%> 
         <input type="password" id="passwordConfirm"  name="passwordConfirm" required><br>
    <%
    }
%>
 <button type="submit"> Update password</button>
    </form>
    </div>
</body>
</html>