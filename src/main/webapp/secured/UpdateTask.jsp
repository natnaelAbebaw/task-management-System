<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;400;700;900&family=Moirai+One&display=swap" rel="stylesheet">
    <title>Task Form</title>
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
            min-height: 120vh;
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

       label{
        font-size: 1rem;
        margin-bottom: 5px;
        display:inline-block;
       }
       select,textArea ,input{
         width:100%;
         padding: 8px 1rem;
         border: 1px solid #F1998F;
         border-radius: 3px;
         background-color:#fff;
         color:#e74c3c;
         font-family: inherit;
       }
       
       textArea{
       height: 7rem;
       }
       
       select:focus,textArea:focus,input:focus{
         font-family: inherit;
         outline:1px solid #e74c3c;
       }
       
        textArea,input{
         margin-bottom: 1rem;
       }
         select{
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
  
    <div class="box">
    <h2>Add tasks to your task store.</h2>
      
    <a class="back" href="/TaskManagementSystem/secured/tasks"><ion-icon name="arrow-back-outline"></ion-icon></a>
    <%
   Object ErrorMessage = request.getAttribute("error"); 
   String title =(String)  request.getAttribute("title"); 
   String description =(String)  request.getAttribute("description"); 
   String dueDate =(String) request.getAttribute("dueDate"); 
   String priority =(String)  request.getAttribute("priority"); 
   int id =(int)  request.getAttribute("id"); 
    if (ErrorMessage != null) {
%>
   
    <div class="errorMessage">
    <h3>Error occur:</h3>
        <%= ErrorMessage %>
    </div>
<%
    }
%>

    <form action="/TaskManagementSystem/secured/updateTask/${id}" method="get">
        <label for="title">Title</label>
                        <%
    if (title != null) {
%> 
        <input type="text" id="title" value=<%=title  %> name="title" required><br>
<%
    }else{
    	%> 
        <input type="text" id="title"  name="title" required><br>

    <%
    }
%>
    
            <label for="description">Description</label>
                        <%
    if (description != null) {
%> 
        <textarea type="text" id="description"  name="description" required><%=description  %></textarea><br>
<%
    }else{
    	%> 
        <textarea type="text" id="description"  name="description" required></textarea><br>

    <%
    }    
    %>                    
                        
        <label for="dueDate">Due date</label> <br />
                              <%
    if (dueDate != null) {
%> 
        <input type="date" id="dueDate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${dueDate}'/>" name="dueDate" required><br>
<%
    }else{
    	%> 
         <input type="date" id="dueDate" name="dueDate" required><br>
    <%
    }
%>

     <label for="priority">Priority</label> <br />
   <%    if (priority != null) {
%> 
        <select id="priority" name="priority"  value=<%=priority%> >
        <option value="high">High Priority</option>
        <option value="medium">Medium Priority</option>
        <option value="low">Low Priority</option>
       </select>
<%
    }else{
    	%> 
          <select id="priority" name="priority" >
        <option value="high">High Priority</option>
        <option value="medium">Medium Priority</option>
        <option value="low">Low Priority</option>
       </select>
    <%
    }
%>
        
        <button type="submit"> Add Task</button>
    </form>
    </div>
</body>
</html>