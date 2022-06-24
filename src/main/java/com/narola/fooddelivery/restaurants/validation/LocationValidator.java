package com.narola.fooddelivery.restaurants.validation;

import com.narola.fooddelivery.location.Location;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LocationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Location.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Location location = (Location) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine", "location.addressline");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "area", "location.area");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "location.city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "location.state");
//        if (!StringUtils.hasText(String.valueOf(location.getPincode()))) {
//            errors.rejectValue("pincode", "localtion.pincode.empty", null);
//        } else {
//            errors.rejectValue("pincode", "localtion.pincode.invalid", new Object[]{location.getPincode()}, null);
//        }
    }
}
