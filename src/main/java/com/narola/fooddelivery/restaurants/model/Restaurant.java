package com.narola.fooddelivery.restaurants.model;

import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.restaurants.dao.RestDAOMYSQL;
import com.narola.fooddelivery.user.User;
import com.narola.fooddelivery.user.UserDAO;
import com.narola.fooddelivery.utility.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private int rating;
    private int locationId;
    private int disableFlag;
    private String email;

    private MultipartFile restPic;

    private Location location;
    private String restphotoAsBase64;
    private ArrayList<Dish> restaurantMenu = null;
    private ArrayList<String> categoriesList = null;
    private int restaurantUserId;
    private User user;
    private Timestamp timestamp;

    @Autowired
    RestDAOMYSQL restDAOMYSQL;

    @Autowired
    UserDAO userDAO;

    public MultipartFile getRestPic() {
        return restPic;
    }

    public void setRestPic(MultipartFile restPic) {
        this.restPic = restPic;
    }


    public Timestamp getTimestamp() {
        if (timestamp == null) setTimestamp(restDAOMYSQL.getJoinDate(restaurantId));
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return restaurantUserId;
    }

    public void setUserId(int userId) {
        restaurantUserId = userId;
    }

    public User getUser() {
        if (user == null && restaurantUserId != 0) return userDAO.findByUserId(restaurantUserId);
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCategories() {
        if (categoriesList == null) return (ArrayList<String>) restDAOMYSQL.getRestaurantCategories(restaurantId);
        return categoriesList;
    }

    public void setCategories(ArrayList<String> categories) {
        categoriesList = categories;
    }

    public List<Dish> getMenu() {
        if (restaurantMenu == null)
            return (ArrayList<Dish>) DAOFactory.getInstance().getDishDAO().getRestaurantMenu(restaurantId);
        return restaurantMenu;
    }

    public void setMenu(ArrayList<Dish> menu) {
        restaurantMenu = menu;
    }


    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getRestId() {
        return restaurantId;
    }


    public String getRestName() {
        return restaurantName;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public ArrayList<Dish> getRestaurantMenu() {
        return restaurantMenu;
    }

    public void setRestaurantMenu(ArrayList<Dish> restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
    }

    public ArrayList<String> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public int getRestaurantUserId() {
        return restaurantUserId;
    }

    public void setRestaurantUserId(int restaurantUserId) {
        this.restaurantUserId = restaurantUserId;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(int disableFlag1) {
        disableFlag = disableFlag1;
    }

    @Override
    public String toString() {
        return "Restaurant [RestId=" + restaurantId + ", RestName=" + restaurantName + ", Rating=" + rating + ", LocId=" + locationId + ", DisableFlag=" + disableFlag + ", email=" + email + ", location=" + location + ", Menu=" + restaurantMenu + ", Categories=" + categoriesList + ", restphotoAsBase64=" + restphotoAsBase64 + "]";
    }

}
