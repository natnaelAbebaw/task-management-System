<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;400;700;900&display=swap" rel="stylesheet">

<title>Insert title here</title>
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

       label{
        font-size: 1rem;
        margin-bottom: 5px;
        display:inline-block;
       }
       input{
         width:100%;
         padding: 8px 1rem;
         border: 1px solid #F1998F;
         border-radius: 3px;
       
       font-family: inherit;
       }
       
       input:focus{
         font-family: inherit;
         outline:1px solid #e74c3c;
       }
       
        input[id="email"]{
         margin-bottom: 1rem;
       }
          input[id="password"]{
         margin-bottom: 2rem;
       }
       
            h3 {
            color: #e74c3c;
            font: 1.8rem;
            margin-bottom: 0.5rem;
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
  <div class="loginBox">
    <h2>login your credentials</h2>
    
    <%
   Object ErrorMessage = request.getAttribute("error"); 
   String password =(String)  request.getAttribute("password"); 
   String email =(String)  request.getAttribute("email"); 
    if (ErrorMessage != null) {
%>
   
    <div class="errorMessage">
    <h3>Error occur:</h3>
        <%= ErrorMessage %>
    </div>
<%
    }
%>

    <form action="login" method="get">
        <label for="email">Email</label> <br />
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
        
        <label for="password">Password</label> <br />
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
        <button type="submit"> login</button>
    </form>
     
     <p> If you don't have an account <a href="signup.jsp">click here to sign up.</a></p>
    </div>
</body>
</html>