package com.narola.fooddelivery.utility;

import com.narola.fooddelivery.dishes.dao.DishDAOPostGRESQL;
import com.narola.fooddelivery.dishes.dao.IDishDAO;
import com.narola.fooddelivery.restaurants.dao.IRestDAO;
import com.narola.fooddelivery.restaurants.dao.RestDAOPOSTGRESQL;

public class PostGresDaoConfig implements IDaoConfig{

    public IRestDAO getRestDAO() {
        return new RestDAOPOSTGRESQL();
    }

    public IDishDAO getDishDAO() {
        return new DishDAOPostGRESQL();
    }
}
