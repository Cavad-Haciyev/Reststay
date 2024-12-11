package com.reststay.restaurant_service.repository;

import com.reststay.restaurant_service.model.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurants,String> {

    Optional<Restaurants> findByName(String name);
    Optional<Restaurants> findRestaurantsByOwnerId(String ownerId);
}
