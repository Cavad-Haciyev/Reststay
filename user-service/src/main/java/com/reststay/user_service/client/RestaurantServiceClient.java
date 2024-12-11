package com.reststay.user_service.client;

import com.reststay.restaurant_service.dto.RestaurantResponseForUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",path = "/api/restaurants")
public interface RestaurantServiceClient {
    @GetMapping("/restaurantById/{ownerId}")
    ResponseEntity<RestaurantResponseForUser> getRestaurantById(@PathVariable String ownerId);
}
