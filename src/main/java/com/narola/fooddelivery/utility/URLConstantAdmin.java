package com.narola.fooddelivery.utility;

import org.springframework.stereotype.Component;

@Component
public class URLConstantAdmin {

	public static String PREFIX_PAGES = "/WEB-INF/pages";

	public static String DASHBOARD = "/AdminHomeServlet";
	public static String DASHBOARDJSP="/WEB-INF/pages/Admin/index.jsp";

	public static String UPDATEDISH_JSP = "/WEB-INF/pages/Admin/UpdateDish.jsp";
	public static String ADDDISH_JSP = "/WEB-INF/pages/Admin/Adddish.jsp";

	public static String SEARCHDISH_JSP = "/WEB-INF/pages/Admin/Searchdish.jsp";

	public static String ADDRESTAURANT_JSP = "/WEB-INF/pages/Admin/AddRest.jsp";
	public static String SEARCHRESTAURANT_JSP = "/WEB-INF/pages/Admin/SearchRestaurants.jsp";

	public static String UPDATERESTAURANT_JSP = "/WEB-INF/pages/Admin/UpdateRestaurant.jsp";

	public static String ADDCATEGORY_JSP = "/WEB-INF/pages/Admin/AddCategory.jsp";
	public static String SUBCATEGORIES_JSP = "/WEB-INF/pages/Admin/SubCategories.jsp";


	public static String RESTDETAIL_JSP = "/WEB-INF/pages/Admin/RestDetail.jsp";

	public static String REGISTER_JSP = "/WEB-INF/pages/Admin/Register.jsp";

	public static String LOGIN_JSP = "/WEB-INF/pages/Admin/Login.jsp";

	public static String ADDUSER_JSP = "/WEB-INF/pages/Admin/AddUser.jsp";

	public static String VIEWUSER_JSP = "/WEB-INF/pages/Admin/ViewUser.jsp";

	public static String UPDATEUSER_JSP = "/WEB-INF/pages/Admin/UpdateUser.jsp";

	public static String PROFILE_JSP = "/WEB-INF/pages/Admin/Profile.jsp";
	
	public static String NEW_ORDER_JSP = "/WEB-INF/pages/Admin/NewOrder.jsp";
	public static String NEWORDERRESTAURANT_JSP = "/WEB-INF/pages/Admin/NewOrderRestaurant.jsp";
	public static String PROCESSING_ORDER_JSP = "/WEB-INF/pages/Admin/OrderInProcess.jsp";
	public static String COMPLETED_ORDER_JSP = "/WEB-INF/pages/Admin/CompletedOrders.jsp";
	
	public static String LOGOUT = "/Logout";

}
