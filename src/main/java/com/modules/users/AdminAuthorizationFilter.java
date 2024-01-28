package com.modules.users;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AdminAuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

   	   UserModel user = (UserModel) session.getAttribute("user");
	 
      
        if (user.getRole().equals("admin")) {
            chain.doFilter(request, response);
        } else {
        	request.setAttribute("error", "An error occurred: " + "You are not authorized to do this activity!" );
            request.getRequestDispatcher("/globalErrorHandler").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}