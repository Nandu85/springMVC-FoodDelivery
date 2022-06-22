package com.narola.fooddelivery.utility;

import org.springframework.stereotype.Component;

@Component
public class URLConstantUser {

    public static String DASHBOARD_SERVLET = "/UserHomePage";

    public static String SEARCHRESTAURANT_JSP = "/User/SearchRestaurants.jsp";

    public static String CATEGORYDETAIL_JSP = "/User/CategoryDetail.jsp";
    public static String RESTDETAIL_JSP = "/User/RestDetail.jsp";

    public static String DASHBOARD = "/User/index.jsp";
    public static String PROFILE_JSP = "/User/Profile.jsp";

    public static String PLACE_ORDER_JSP = "/User/PlaceOrder.jsp";
    public static String CART_JSP = "/User/Cart.jsp";
    public static String ADDLOCATION_JSP = "/User/AddLocation.jsp";
}
