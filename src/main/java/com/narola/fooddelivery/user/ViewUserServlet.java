package com.narola.fooddelivery.user;

import com.narola.fooddelivery.utility.URLConstantAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ViewUser_servlet
 */
public class ViewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	UserDAO userDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", userDao.findAllUser());
		getServletContext().getRequestDispatcher(URLConstantAdmin.VIEWUSER_JSP).forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
