package com.narola.fooddelivery.restaurants.controller;

import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.restaurants.model.RestaurantRequest;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private Validator restaurantRequestValidator;

    @Autowired
    private Validator locationValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(restaurantRequestValidator);
    }

    @GetMapping("/AddRestaurant")
    public ModelAndView getAddRestaurant() {
        ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
        return modelAndView;
    }

    @PostMapping(value = "/AddRestaurant", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postAddRestaurant(@Validated RestaurantRequest restaurantRequest, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
            modelAndView.addObject("ErrMsg", result.getAllErrors());
            return modelAndView;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurantService.addRestaurant(restaurantRequest.getLocation(), restaurantRequest.getRestPic(), restaurant);

        ModelAndView modelAndView = new ModelAndView("redirect:SearchRestaurant");
        return modelAndView;


    }

//    public ModelAndView searchRestaurant(){
//
//    }



}
