package com.narola.fooddelivery.restaurants.controller;

import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.utility.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@Controller
public class RestaurantController {

    @GetMapping("/AddRestaurant")
    protected ModelAndView doGetS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
        modelAndView.addObject("ErrMsg", "New Jsp is here");
//        User user = (User) request.getSession().getAttribute("user");
//        int usertype = user.getAdmin();
//        if (usertype == Constant.ADMIN_SUPERADMIN || usertype == Constant.ADMIN_ADMIN)
//            getServletContext().getRequestDispatcher(URLConstantAdmin.ADDRESTAURANT_JSP).forward(request, response);
        return modelAndView;
    }

    @PostMapping(value = "/AddRestaurant", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView doRestaurantPost(@RequestParam String restaurantName, @RequestParam String email, Location location, @RequestPart MultipartFile restPic) {
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(restaurantName);
            restaurant.setEmail(email);
            IRestaurantService restService = ServiceFactory.getInstance().getRestaurantService();
            restService.addRestaurant(location, restPic, restaurant);

            ModelAndView modelAndView = new ModelAndView("redirect:SearchRestaurant");
            return modelAndView;

//            response.sendRedirect(request.getContextPath() + URLConstantOfServlet.SEARCHRESTAURANT);

        } catch (Exception e) {

            ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
            modelAndView.addObject("ErrMsg", e.getMessage());

            return modelAndView;
//            request.getServletContext().getRequestDispatcher(URLConstantAdmin.PREFIX_PAGES+URLConstantAdmin.ADDRESTAURANT_JSP).include(request, response);

        }

    }

    @GetMapping("/DishDetail")
    protected ModelAndView doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
//        if (id != null) {
//            try {
        IRestaurantService restService = ServiceFactory.getInstance().getRestaurantService();
        ModelAndView modelAndView = new ModelAndView("User/CategoryDetail");
        modelAndView.addObject("SubCategory", restService.getSubCategoryById(id));
        modelAndView.addObject("Restaurants", restService.getRestaurantFromSubCategory(id));
//            } catch (ServletException | IOException e) {
//                e.printStackTrace();
//                req.setAttribute("errMsg", Constant.ERR_SOMETHING_WRONG);
//                getServletContext().getRequestDispatcher(URLConstantUser.CATEGORYDETAIL_JSP).forward(req, resp);
//            }
//        }

        return modelAndView;
    }


}
