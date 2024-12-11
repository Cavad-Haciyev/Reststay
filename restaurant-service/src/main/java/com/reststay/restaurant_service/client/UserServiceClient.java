package com.reststay.restaurant_service.client;


import com.reststay.restaurant_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service",path = "/api/user")
public interface UserServiceClient {
    @GetMapping("/validate")
    String validateToken(@RequestHeader("Authorization") String token);

    @PutMapping("/{userId}/addRestaurant")
  ResponseEntity<Void> addRestaurantToUser(@PathVariable String userId, @RequestBody String restaurantId);
    @GetMapping("/user/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable  String id);
}
