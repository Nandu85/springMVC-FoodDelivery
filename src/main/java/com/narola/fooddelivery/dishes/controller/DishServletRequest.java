package com.narola.fooddelivery.dishes.controller;

import com.narola.fooddelivery.category.Category;
import com.narola.fooddelivery.category.CategoryDAO;
import com.narola.fooddelivery.category.SubCategory;
import com.narola.fooddelivery.category.SubCategoryDAO;
import com.narola.fooddelivery.dishes.model.Dish;
import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.utility.DAOFactory;

import java.io.IOException;
import java.util.List;

public class DishServletRequest {
	
	public Dish getDish(int dishId) {
		try {
			return DAOFactory.getInstance().getDishDAO().DishFromId(dishId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Restaurant> getRestaurants() {
		return DAOFactory.getInstance().getRestDAO().getAllRestaurants();
	}
	
	public List<Category> getCategories() {
		return CategoryDAO.getAllCategories();
	}
	
	public List<SubCategory> getSubCategories() {
		return SubCategoryDAO.getAllSubCategories();
	}
	
}
