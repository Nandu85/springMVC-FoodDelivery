package com.narola.fooddelivery.user;

import com.narola.fooddelivery.utility.URLConstantOfServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DeleteUser_servlet
 */
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	UserDAO userDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("UserId")!=null)
		userDAO.DeleteUser(userDAO.findByUserId(Integer.parseInt(request.getParameter("UserId"))));
		response.sendRedirect(request.getContextPath()+URLConstantOfServlet.VIEWUSER);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
