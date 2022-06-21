package com.narola.fooddelivery.dishes.controller;

import com.narola.fooddelivery.dishes.DishException;
import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.utility.Constant;
import com.narola.fooddelivery.utility.ServiceFactory;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class deleteDish
 */
public class DeleteDishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String dishId = request.getParameter("DishId");

			IDishService dishService = ServiceFactory.getInstance().getDishService();

			dishService.deleteDish(dishId);

			response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHDISH_WITH_NO_FILTER);
		} catch (IOException e) {
			throw new DishException(Constant.ERR_SOMETHING_WRONG);
		}

	}

}
