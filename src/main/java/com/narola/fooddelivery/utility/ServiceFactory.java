package com.narola.fooddelivery.utility;

import com.narola.fooddelivery.dishes.service.IDishService;
import com.narola.fooddelivery.dishes.service.impl.DishServiceImpl;
import com.narola.fooddelivery.restaurants.service.IRestaurantService;
import com.narola.fooddelivery.restaurants.service.impl.RestaurantServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactory {

	private static ServiceFactory SERVICE_HELPER = null;

	public static IRestaurantService restaurantService = null;

	public static IDishService dishService = null;

	@Bean("ServiceFactory")
	public static ServiceFactory getInstance() {
		if (SERVICE_HELPER == null) {
			SERVICE_HELPER = new ServiceFactory();
		}
		return SERVICE_HELPER;
	}

	@Bean("RestaurantServiceImpl")
	public IRestaurantService getRestaurantService() {
		if(restaurantService == null)
			restaurantService = new RestaurantServiceImpl();
		return restaurantService;
	}

	@Bean("DishServiceImpl")
	public IDishService getDishService() {
		if(dishService == null)
			dishService = new DishServiceImpl();
		return dishService;
	}
}
