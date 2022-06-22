package com.narola.fooddelivery.restaurants.model;

import com.narola.fooddelivery.location.Location;
import org.springframework.web.multipart.MultipartFile;

public class RestaurantRequest {

    private String restaurantId;
    private String restaurantName;
    private String email;
    private MultipartFile restPic;

    private Location location;

    private String disable;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getRestPic() {
        return restPic;
    }

    public void setRestPic(MultipartFile restPic) {
        this.restPic = restPic;
    }
}
