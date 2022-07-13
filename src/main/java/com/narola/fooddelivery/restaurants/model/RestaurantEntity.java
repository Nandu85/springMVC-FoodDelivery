package com.narola.fooddelivery.restaurants.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restid;

    @Column(nullable = false)
    private String restname;

    @Column(nullable = false)
    private int Disabled;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(name = "Pic")
    private String restphotoAsBase64;

    @Column(name = "Location")
    private int locationId;

    public int getRestaurantId() {
        return restid;
    }

    public void setRestaurantId(int restaurantId) {
        this.restid = restaurantId;
    }

    public String getRestaurantName() {
        return restname;
    }

    public void setRestaurantName(String restaurantName) {
        this.restname = restaurantName;
    }

    public int getDisableFlag() {
        return Disabled;
    }

    public void setDisableFlag(int disableFlag) {
        this.Disabled = disableFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestphotoAsBase64() {
        return restphotoAsBase64;
    }

    public void setRestphotoAsBase64(String restphotoAsBase64) {
        this.restphotoAsBase64 = restphotoAsBase64;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
