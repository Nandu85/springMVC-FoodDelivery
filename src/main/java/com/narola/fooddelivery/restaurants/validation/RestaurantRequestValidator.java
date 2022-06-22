package com.narola.fooddelivery.restaurants.validation;

import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.restaurants.model.RestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RestaurantRequestValidator implements Validator {

    @Autowired
    LocationValidator locationValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return RestaurantRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RestaurantRequest request = (RestaurantRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "restaurantName", "restaurant.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "restaurant.email");
        errors.pushNestedPath("location");
        Location location = request.getLocation();
        ValidationUtils.invokeValidator(locationValidator, request.getLocation(), errors);
    }
}
