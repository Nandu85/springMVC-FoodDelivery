package com.narola.fooddelivery.utility;

import com.narola.fooddelivery.dishes.dao.DishDAOMYSQL;
import com.narola.fooddelivery.dishes.dao.IDishDAO;
import com.narola.fooddelivery.restaurants.dao.IRestDAO;
import com.narola.fooddelivery.restaurants.dao.RestDAOMYSQL;

public class MYSQLDaoConfig implements IDaoConfig{

    public IRestDAO getRestDAO() {
        return new RestDAOMYSQL();
    }

    public IDishDAO getDishDAO() {
        return new DishDAOMYSQL();
    }

}
