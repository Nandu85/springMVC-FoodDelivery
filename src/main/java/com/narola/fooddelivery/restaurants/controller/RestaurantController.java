package com.narola.fooddelivery.restaurants.controller;

import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.restaurants.model.RestaurantRequest;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.utility.Constant;
import com.narola.fooddelivery.utility.URLConstantOfServlet;
import com.narola.fooddelivery.utility.URLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @Autowired
    private Validator restaurantRequestValidator;

    @Autowired
    private MessageSource messageSource;

    @InitBinder("restaurantRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(restaurantRequestValidator);
    }


    @GetMapping(value = URLConstantOfServlet.ADDRESTAURANT,produces = "text/html;charset=UTF-8")
    public ModelAndView getAddRestaurant(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
        modelAndView.addObject("title", messageSource.getMessage("res.form.title", null, locale));
        return modelAndView;
    }

    @PostMapping(value = URLConstantOfServlet.ADDRESTAURANT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/html;charset=UTF-8")
    public ModelAndView postAddRestaurant(@Validated RestaurantRequest restaurantRequest, BindingResult result,Locale locale) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
            List<String> errors = new ArrayList<>();
            for (ObjectError err : result.getAllErrors()) {
                errors.add(messageSource.getMessage(err.getCode(), null, locale));
            }
            modelAndView.addObject("title", messageSource.getMessage("res.form.title", null, locale));
            modelAndView.addObject("ErrMsg", errors);
            return modelAndView;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurantService.addRestaurant(restaurantRequest.getLocation(), restaurantRequest.getRestPic(), restaurant);

        ModelAndView modelAndView = new ModelAndView("redirect:SearchRestaurant");
        return modelAndView;
    }

    @GetMapping(URLConstantOfServlet.VIEWDISH)
    public ModelAndView getDishDetail(@RequestParam String id) {
        if (id == null || id.equals("0")) throw new URLException("Id is missing");
        ModelAndView modelAndView = new ModelAndView("User/CategoryDetail");
        modelAndView.addObject("SubCategory", restaurantService.getSubCategoryById(id));
        modelAndView.addObject("Restaurants", restaurantService.getRestaurantFromSubCategory(id));
        return modelAndView;
    }

    @GetMapping("/errors")
    public ModelAndView getError() throws ServletException, IOException {
        throw new URLException("Invalid url");
    }

    @GetMapping(URLConstantOfServlet.SEARCHRESTAURANT)
    public ModelAndView getAllRestaurants(HttpSession session, String restaurantName, String area) {
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView;
        if (user != null && (user.getAdmin() == Constant.ADMIN_SUPERADMIN || user.getAdmin() == Constant.ADMIN_ADMIN))
            modelAndView = new ModelAndView("Admin/SearchRestaurants");
        else modelAndView = new ModelAndView("User/SearchRestaurants");

        if (restaurantName != null || area != null)
            modelAndView.addObject("Restaurants", restaurantService.searchRestaurants(restaurantName, area));
        else modelAndView.addObject("Restaurants", restaurantService.getRestaurants());
        modelAndView.addObject("Areas", restaurantService.getAreas());
        return modelAndView;
    }

    @GetMapping(URLConstantOfServlet.UPDATERESTAURANT)
    public ModelAndView updateRestaurantDetail(@RequestParam String RestaurantId) {
        if (RestaurantId == null) throw new URLException("Id is missing in UpdateRestaurant");
        ModelAndView modelAndView = new ModelAndView("Admin/UpdateRestaurant");
        modelAndView.addObject("Restaurant", restaurantService.getRestaurantFromId(RestaurantId));
        return modelAndView;
    }

    @PostMapping(value = URLConstantOfServlet.UPDATERESTAURANT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postUpdateRestaurant(@Validated RestaurantRequest restaurantRequest, BindingResult result) {
        if (restaurantRequest.getRestaurantId() == null || restaurantRequest.getRestaurantId().equals("0")) {
            throw new URLException("Please provide id First");
        }
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Admin/UpdateRestaurant");
            List<String> errors = new ArrayList<>();
            for (ObjectError err : result.getAllErrors()) {
                String x = null;
                if (err.getCode() == "typeMismatch") x = err.getObjectName();
                String msg = messageSource.getMessage(err.getCode(), new Object[]{x}, Locale.getDefault());
                errors.add(msg);
            }
            modelAndView.addObject("Restaurant", restaurantService.getRestaurantFromId(restaurantRequest.getRestaurantId()));
            modelAndView.addObject("ErrMsg", errors);
            return modelAndView;
        }

        restaurantService.updateRestaurant(restaurantRequest.getLocation(), restaurantRequest.getRestPic(), restaurantRequest);

        ModelAndView modelAndView = new ModelAndView("redirect:SearchRestaurant");
        return modelAndView;
    }

    @GetMapping(URLConstantOfServlet.RESTDETAIL)
    public ModelAndView getRestaurantMenu(HttpSession session, @RequestParam(name = "RestaurantId") String id) {
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView;
        if (user != null && (user.getAdmin() == Constant.ADMIN_SUPERADMIN || user.getAdmin() == Constant.ADMIN_ADMIN))
            modelAndView = new ModelAndView("Admin/RestDetail");
        else modelAndView = new ModelAndView("User/RestDetail");

        modelAndView.addObject("Restaurant", restaurantService.getRestaurantFromId(id));
        return modelAndView;
    }

    @PostMapping(value = URLConstantOfServlet.ADDRESTAURANT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = "language")
    public ModelAndView postHeaderAddRestaurant(@Validated RestaurantRequest restaurantRequest, BindingResult result, HttpServletRequest req) {

        ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
        List<String> errors = new ArrayList<>();
        String lang = req.getHeader("language");
        Locale locale = new Locale(lang);
        for (ObjectError err : result.getAllErrors()) {
            errors.add(messageSource.getMessage(err.getCode(), err.getArguments(), locale));
        }
        modelAndView.addObject("ErrMsg", errors);
        return modelAndView;
    }

    @GetMapping("/local")
    public ResponseEntity<String> localResolverTesting(Locale locale, @RequestParam String key) {
        return ResponseEntity.ok().body(locale.toString() + "=" + messageSource.getMessage(key, null, locale));
    }

}
