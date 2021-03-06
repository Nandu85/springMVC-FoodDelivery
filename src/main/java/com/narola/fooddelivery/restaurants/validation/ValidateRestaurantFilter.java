package com.narola.fooddelivery.restaurants.validation;

import com.narola.fooddelivery.restaurants.RestaurantException;
import com.narola.fooddelivery.utility.DAOFactory;
import com.narola.fooddelivery.utility.URLConstantAdmin;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet Filter implementation class ValidateRestaurant_filter
 */
public class ValidateRestaurantFilter implements Filter {

    public ValidateRestaurantFilter() {}
    
    public void destroy() {}
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	HttpServletRequest req=(HttpServletRequest)request;
    	
    	if(req.getMethod().equals("POST")) {
    		try {
    			String restId=request.getParameter("RestaurantId");
    			String restaurantName = request.getParameter("RestName");
        		String email = request.getParameter("email");
        		
        		if(restaurantName == null || restaurantName.trim().isEmpty()) {
        			throw new RestaurantException("Please Enter Restaurant Name...");
        		} else if(email == null || email.trim().isEmpty()) {
        			throw new RestaurantException("Please Enter email...");
        		}
    			chain.doFilter(request, response);

        		
        	}
        	catch (Exception e) {
        		req.setAttribute("ErrMsg", e.getMessage());
        		if(req.getRequestURI().contains(URLConstantOfServlet.ADDRESTAURANT)) {
        			req.getRequestDispatcher(URLConstantAdmin.ADDRESTAURANT_JSP).include(req, response);
        		}
        			
        		else if(req.getRequestURI().contains(URLConstantOfServlet.UPDATERESTAURANT)) {
        			req.setAttribute("Restaurant", DAOFactory.getInstance().getRestDAO().getRestaurantFromId(Integer.parseInt(request.getParameter("RestaurantId"))));
        			req.getRequestDispatcher(URLConstantAdmin.UPDATERESTAURANT_JSP).include(req, response);
        		}
        			

    		}
    		
        	
    	}else {
    			chain.doFilter(request, response);
    	}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {}

}
