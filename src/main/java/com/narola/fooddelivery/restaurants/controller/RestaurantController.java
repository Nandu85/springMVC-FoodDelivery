package com.narola.fooddelivery.restaurants.controller;

import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.restaurants.model.RestaurantRequest;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.utility.URLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private Validator restaurantRequestValidator;

    @Autowired
    private Validator locationValidator;

    @Autowired
    private MessageSource messageSource;



    @InitBinder("restaurantRequest")
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
            List<String> errors = new ArrayList<>();
            for (ObjectError err:result.getAllErrors()) {
                String x = null;
                if(err.getCode()=="typeMismatch")
                    x=err.getObjectName();
                String msg = messageSource.getMessage(err.getCode(),new Object[]{x}, Locale.getDefault());
                errors.add(msg);
            }
            modelAndView.addObject("ErrMsg", errors);
//            messageSource.getMessage("restaurant.email",null, Locale.getDefault());
            return modelAndView;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurantService.addRestaurant(restaurantRequest.getLocation(), restaurantRequest.getRestPic(), restaurant);

        ModelAndView modelAndView = new ModelAndView("redirect:SearchRestaurant");
        return modelAndView;


    }

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
