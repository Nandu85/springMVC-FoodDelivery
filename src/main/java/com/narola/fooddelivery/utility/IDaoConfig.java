package com.narola.fooddelivery.utility;

import com.narola.fooddelivery.dishes.dao.IDishDAO;
import com.narola.fooddelivery.restaurants.dao.IRestDAO;

public interface IDaoConfig {

    public IRestDAO getRestDAO();

    public IDishDAO getDishDAO();
}
