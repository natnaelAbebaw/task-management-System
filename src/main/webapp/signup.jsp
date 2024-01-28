<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;400;700;900&family=Moirai+One&display=swap" rel="stylesheet">
<title>sign</title>
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
        

        .loginBox {
            width: 35%;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
       
    </style>
</head>
<body>


<%
   Object ErrorMessage = request.getAttribute("error"); 
   String username =(String) request.getAttribute("username"); 
   String password =(String)  request.getAttribute("password"); 
   String email =(String)  request.getAttribute("email"); 
   String passwordConfirm =(String)  request.getAttribute("passwordConfirm"); 
   
    if (ErrorMessage != null) {
%>
   
    <div class="errorMessage">
    <h3>Error occur:</h3>
        <%= ErrorMessage %>
    </div>
<%
    }
%>

<div class="loginBox">
    <h2>Sign up to Tasker</h2>
    <form action="signup" method="get">
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

        <label for="password">Password</label>
        
                       <%
    if (password != null) {
%> 
        <input type="password" id="password" value=<%=password%> name="password" required><br>
<%
    }else{
    	%> 
         <input type="password" id="password" name="password" required><br>
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
 <button type="submit"> Sign up</button>
    </form>
    </div>
</body>
</html>