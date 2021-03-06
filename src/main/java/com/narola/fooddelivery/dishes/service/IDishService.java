package com.narola.fooddelivery.dishes.service;

import com.narola.fooddelivery.category.Category;
import com.narola.fooddelivery.dishes.model.Dish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;

public interface IDishService {

	public void addDish(Dish dish, Part photoPart);
	
	public void updateDish(Dish dish, HttpServletRequest request);
	
	public List<Dish> searchDish(HttpServletRequest request, HttpServletResponse response, String dname, String categoryId,
			String dtype1, String isFilter);
	
	public List<Category> getCategories();
	
	public void deleteDish(String dishId);
}
