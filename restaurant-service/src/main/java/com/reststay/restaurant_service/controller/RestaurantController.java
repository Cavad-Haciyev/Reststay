package com.reststay.restaurant_service.controller;

import com.reststay.restaurant_service.baseresponse.ResponseModelService;
import com.reststay.restaurant_service.dto.CreateRestaurantRequest;
import com.reststay.restaurant_service.dto.CreateRestaurantResponse;
import com.reststay.restaurant_service.dto.RestaurantResponseForUser;
import com.reststay.restaurant_service.dto.UpdateRestaurantRequest;
import com.reststay.restaurant_service.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

  @PostMapping("/create")
    public ResponseEntity<?> createRestaurant( @RequestBody CreateRestaurantRequest request,@RequestHeader("Authorization") String token) {
        return ResponseModelService.responseBuilder(
                LocalDateTime.now(),
                restaurantService.createRestaurant(request,token)
                ,
                "Successfully",
                HttpStatus.OK);
//        ,
    }
    @GetMapping("/restaurantByName/{name}")
    public ResponseEntity<Object> getRestaurantByName(@PathVariable String name) {
        return ResponseModelService.responseBuilder(
                LocalDateTime.now(),
                restaurantService.getRestaurantByName(name)
                ,
                "Successfully",
                HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<Object> getAllRestaurant() {
        return ResponseModelService.responseBuilder(
                LocalDateTime.now(),
                restaurantService.getAllRestaurants()
                ,
                "Successfully",
                HttpStatus.OK);
    }
    @GetMapping("/restaurantById/{ownerId}")
    public ResponseEntity<RestaurantResponseForUser> getRestaurantById(@PathVariable String ownerId) {
        return ResponseEntity.ok(restaurantService.getRestaurantByOwnerId(ownerId));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateRestaurant(@PathVariable String id, @RequestBody UpdateRestaurantRequest request) {
        return ResponseModelService.responseBuilder(
                LocalDateTime.now(),
                restaurantService.updateRestaurant(id,request)
                ,
                "Successfully",
                HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRestaurant(@PathVariable String id) {
        return ResponseModelService.responseBuilder(
                LocalDateTime.now(),
                restaurantService.deleteRestaurant(id)
                ,
                "Successfully",
                HttpStatus.OK);
    }

}
