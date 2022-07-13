package com.narola.fooddelivery.restaurants.repository;

import com.narola.fooddelivery.restaurants.model.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<RestaurantEntity,Integer> {
}
