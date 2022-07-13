package com.narola.fooddelivery.restaurants.controller;

import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.restaurants.model.RestaurantEntity;
import com.narola.fooddelivery.restaurants.model.RestaurantRequest;
import com.narola.fooddelivery.restaurants.repository.RestaurantRepository;
import com.narola.fooddelivery.restaurants.service.impl.RestaurantServiceImplJPA;
import com.narola.fooddelivery.utility.URLConstantOfServlet;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class RestaurantJPAController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantServiceImplJPA restaurantService;

    @Autowired
    private Validator restaurantRequestValidator;

    @Autowired
    private MessageSource messageSource;

    @InitBinder("restaurantRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(restaurantRequestValidator);
    }


    @GetMapping(value = URLConstantOfServlet.ADDRESTAURANT+"jpa", produces = "text/html;charset=UTF-8")
    public ModelAndView getAddRestaurant(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
        modelAndView.addObject("title", messageSource.getMessage("res.form.title", null, locale));
        return modelAndView;
    }

    @PostMapping(value = "/AddRestaurantjpa", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/html;charset=UTF-8")
    public ModelAndView postAddRestaurant(@Validated RestaurantRequest restaurantRequest, BindingResult result, Locale locale) throws IOException {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
            Map<String, String> errorss = new HashMap<>();
//            List<String> errors = new ArrayList<>();
            for (ObjectError err : result.getAllErrors()) {
                errorss.put(err.getCode(), messageSource.getMessage(err.getCode(), null, locale));
//                errors.add(messageSource.getMessage(err.getCode(), null, locale));
            }
            modelAndView.addObject("title", messageSource.getMessage("res.form.title", null, locale));
            modelAndView.addObject("ErrMsg", errorss);
            return modelAndView;
        }
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setEmail(restaurantRequest.getEmail());
//=============================================================================//=============================================================================
        restaurantService.addRestaurant(restaurantRequest.getLocation(), restaurantRequest.getRestPic(), restaurant);
//=============================================================================//=============================================================================
        ModelAndView modelAndView = new ModelAndView("redirect:SearchRestaurantjpa");
        return modelAndView;
    }

//    @GetMapping(URLConstantOfServlet.VIEWDISH)
//    public ModelAndView getDishDetail(@RequestParam String id) {
//        if (id == null || id.equals("0")) throw new URLException("Id is missing");
//        ModelAndView modelAndView = new ModelAndView("User/CategoryDetail");
//        modelAndView.addObject("SubCategory", restaurantService.getSubCategoryById(id));
//        modelAndView.addObject("Restaurants", restaurantService.getRestaurantFromSubCategory(id));
//        return modelAndView;
//    }
//
//    @GetMapping("/errors")
//    public ModelAndView getError() throws ServletException, IOException {
//        throw new URLException("Invalid url");
//    }
//
    @GetMapping(URLConstantOfServlet.SEARCHRESTAURANT+"jpa")
    @ResponseBody
    public List<Restaurant> getAllRestaurants(HttpSession session, String restaurantName, String area) {
//        User user = (User) session.getAttribute("user");
//        ModelAndView modelAndView;
//        /*if (user != null && (user.getAdmin() == Constant.ADMIN_SUPERADMIN || user.getAdmin() == Constant.ADMIN_ADMIN))
//            modelAndView = new ModelAndView("Admin/SearchRestaurants");
//        else*/ modelAndView = new ModelAndView("User/SearchRestaurants");
//
//        if (restaurantName != null || area != null)
//            modelAndView.addObject("Restaurants", restaurantService.searchRestaurants(restaurantName, area));
//        else modelAndView.addObject("Restaurants", restaurantService.getRestaurants());
//        modelAndView.addObject("Areas", restaurantService.getAreas());
//        return modelAndView;
        return restaurantService.getRestaurants();
    }
//
//    @GetMapping(URLConstantOfServlet.UPDATERESTAURANT)
//    public ModelAndView updateRestaurantDetail(@RequestParam String RestaurantId) {
//        if (RestaurantId == null) throw new URLException("Id is missing in UpdateRestaurant");
//        ModelAndView modelAndView = new ModelAndView("Admin/UpdateRestaurant");
//        modelAndView.addObject("Restaurant", restaurantService.getRestaurantFromId(RestaurantId));
//        return modelAndView;
//    }
//
//    @PostMapping(value = URLConstantOfServlet.UPDATERESTAURANT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ModelAndView postUpdateRestaurant(@Validated RestaurantRequest restaurantRequest, BindingResult result) {
//        if (restaurantRequest.getRestaurantId() == null || restaurantRequest.getRestaurantId().equals("0")) {
//            throw new URLException("Please provide id First");
//        }
//        if (result.hasErrors()) {
//            ModelAndView modelAndView = new ModelAndView("Admin/UpdateRestaurant");
//            List<String> errors = new ArrayList<>();
//            for (ObjectError err : result.getAllErrors()) {
//                String x = null;
//                if (err.getCode() == "typeMismatch") x = err.getObjectName();
//                String msg = messageSource.getMessage(err.getCode(), new Object[]{x}, Locale.getDefault());
//                errors.add(msg);
//            }
//            modelAndView.addObject("Restaurant", restaurantService.getRestaurantFromId(restaurantRequest.getRestaurantId()));
//            modelAndView.addObject("ErrMsg", errors);
//            return modelAndView;
//        }
//
//        restaurantService.updateRestaurant(restaurantRequest.getLocation(), restaurantRequest.getRestPic(), restaurantRequest);
//
//        ModelAndView modelAndView = new ModelAndView("redirect:SearchRestaurant");
//        return modelAndView;
//    }
//
//    @GetMapping(URLConstantOfServlet.RESTDETAIL)
//    public ModelAndView getRestaurantMenu(HttpSession session, @RequestParam(name = "RestaurantId") String id) {
//        User user = (User) session.getAttribute("user");
//        ModelAndView modelAndView;
//        if (user != null && (user.getAdmin() == Constant.ADMIN_SUPERADMIN || user.getAdmin() == Constant.ADMIN_ADMIN))
//            modelAndView = new ModelAndView("Admin/RestDetail");
//        else modelAndView = new ModelAndView("User/RestDetail");
//
//        modelAndView.addObject("Restaurant", restaurantService.getRestaurantFromId(id));
//        return modelAndView;
//    }
//
//    @PostMapping(value = URLConstantOfServlet.ADDRESTAURANT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = "language")
//    public ModelAndView postHeaderAddRestaurant(@Validated RestaurantRequest restaurantRequest, BindingResult result, HttpServletRequest req) {
//
//        ModelAndView modelAndView = new ModelAndView("Admin/AddRest");
//        List<String> errors = new ArrayList<>();
//        String lang = req.getHeader("language");
//        Locale locale = new Locale(lang);
//        for (ObjectError err : result.getAllErrors()) {
//            errors.add(messageSource.getMessage(err.getCode(), err.getArguments(), locale));
//        }
//        modelAndView.addObject("ErrMsg", errors);
//        return modelAndView;
//    }
//
//    @GetMapping("/local")
//    public ResponseEntity<String> localResolverTesting(Locale locale, @RequestParam String key) {
//        return ResponseEntity.ok().body(locale.toString() + "=" + messageSource.getMessage(key, null, locale));
//    }

//    @GetMapping("persistant-api")
//    public ResponseEntity<String> persistantUnit(@RequestParam String email) {
//        EntityManager manager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//        RestaurantEntity restaurant = new RestaurantEntity();
//        restaurant.setRestaurantName("gfrgdffghdfgh");
//        restaurant.setEmail(email);
////        restaurant.set
//        manager.persist(restaurant);
//        transaction.commit();
//        return ResponseEntity.ok("Donnnnnnne");
//    }

}
