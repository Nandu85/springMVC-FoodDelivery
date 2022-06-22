package com.narola.fooddelivery.restaurants.service;

import com.narola.fooddelivery.category.SubCategory;
import com.narola.fooddelivery.location.Location;
import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.restaurants.model.RestaurantRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.util.List;

public interface IRestaurantService {

	public void addRestaurant(Location location, MultipartFile part, Restaurant restaurant);
	
	public List<Restaurant> searchRestaurants(String restaurantName, String area);
	
	public void updateRestaurant(Location location, MultipartFile restImage, RestaurantRequest restaurantRequest);
	
	public SubCategory getSubCategoryById(String subCatId);
	
	public List<Restaurant> getRestaurantFromSubCategory(String subCatId);
	
	public Restaurant getRestaurantFromId(String restaurantId);
	
	public List<Restaurant> getRestaurants();
	
	public List<String> getAreas();
}
