package com.narola.fooddelivery.cart;

import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.utility.DAOFactory;
import com.narola.fooddelivery.utility.URLConstantOfServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class AddtoCartServlet
 */
public class AddtoCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dishId=request.getParameter("dishId");
		String qty=request.getParameter("qty");
		if(dishId!=null && qty!=null && !dishId.trim().isEmpty() && !qty.trim().isEmpty()) {
			int id=Integer.parseInt(dishId);
			HttpSession session=request.getSession();
			Cart cart=null;
			if(session.getAttribute("Cart")==null) {
				cart=CartDAO.AddCart(DAOFactory.getInstance().getDishDAO().DishFromId(id).getRestId());
				session.setAttribute("Cart", cart);
			}
			cart=(Cart) session.getAttribute("Cart");
			User user=(User) session.getAttribute("user");
			if(user!=null && (cart.getUser()==null || cart.getUser().getUserId()!=user.getUserId())) {
				cart.setUser(user);
				CartDAO.SetCartUser(cart);
			}
			
			CartItem item = new CartItem();
			item.setCart(cart);
			item.setDish(DAOFactory.getInstance().getDishDAO().DishFromId(id));
			item.setQty(Integer.parseInt(qty));
			CartItemsDAO.AddCartItem(item);
			
			
		}
		
		if(request.getRequestURI().contains(URLConstantOfServlet.ADD_TO_CART))
			response.sendRedirect(request.getContextPath()+URLConstantOfServlet.RESTDETAIL+"?RestaurantId="+DAOFactory.getInstance().getDishDAO().DishFromId(Integer.parseInt(dishId)).getRestId());
		else if(request.getRequestURI().contains(URLConstantOfServlet.UPDATE_ITEM))
			response.sendRedirect(request.getContextPath()+URLConstantOfServlet.CHECKOUT);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
