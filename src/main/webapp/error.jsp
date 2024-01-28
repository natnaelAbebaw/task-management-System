<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #333;
            text-align: center;
            padding: 50px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #e74c3c;
        }

        p {
            font-size: 16px;
        }
    </style>
</head>
<body>
 <% Object ErrorMessage = request.getAttribute("error"); %>
    <div class="container">
        <h1>Oops! Something went wrong.</h1>
        <p>We're sorry, but an unexpected error occurred.</p>
        <p>Please try again later or contact support if the problem persists.</p>
        <p>Error: <%= ErrorMessage %></p>
    </div>
</body>
</html>