package com.narola.fooddelivery.restaurants.controller;

import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.utility.URLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class RestaurantController2 {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping("/DishDetail")
    protected ModelAndView getDishDetail(@RequestParam String id) throws ServletException, IOException {
        if (id==null)
            throw new URLException("Id is missing");
        ModelAndView modelAndView = new ModelAndView("User/CategoryDetail");
        modelAndView.addObject("SubCategory", restaurantService.getSubCategoryById(id));
        modelAndView.addObject("Restaurants", restaurantService.getRestaurantFromSubCategory(id));
        return modelAndView;
    }

    @GetMapping("/errors")
    protected ModelAndView getError() throws ServletException, IOException {
       throw new URLException("Invalid url");
    }

}
