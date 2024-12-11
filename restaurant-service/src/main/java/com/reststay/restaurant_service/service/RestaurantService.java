package com.reststay.restaurant_service.service;

import com.reststay.restaurant_service.client.UserServiceClient;
import com.reststay.restaurant_service.dto.*;
import com.reststay.restaurant_service.dto.converter.RestaurantConverter;
import com.reststay.restaurant_service.exception.RestaurantNotFoundException;
import com.reststay.restaurant_service.model.Restaurants;
import com.reststay.restaurant_service.repository.RestaurantRepository;
//import com.reststay.user_service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantConverter restaurantConverter;
    private final UserServiceClient userServiceClient;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantConverter restaurantConverter,UserServiceClient userServiceClient) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantConverter = restaurantConverter;
       this.userServiceClient = userServiceClient;
    }


    public String createRestaurant(CreateRestaurantRequest request,String token) {
        String ownerId = userServiceClient.validateToken(token);
        Restaurants restaurants = restaurantConverter.requestToEntity(request);
        restaurants.setOwnerId(ownerId);
        restaurantRepository.save(restaurants);
        userServiceClient.addRestaurantToUser(ownerId,restaurants.getId());
        return "Restaurant successfully created";

    }


    public RestaurantResponse getRestaurantByName(String name) {

        Restaurants restaurants = restaurantRepository.findByName(name)
                .orElseThrow(RestaurantNotFoundException::new);
        UserResponse user = userServiceClient.getUserById(restaurants.getOwnerId()).getBody();
        RestaurantResponse restaurantResponse = restaurantConverter.entityToRestaurantResponse(restaurants);
         restaurantResponse.setOwner(user);
         return restaurantResponse;

    }



    public List<RestaurantResponse> getAllRestaurants() {
        List<Restaurants> restaurants = restaurantRepository.findAll();
        return restaurants
                .stream()
                .map(restaurantConverter::entityToRestaurantResponse).toList();

    }

    public RestaurantResponse updateRestaurant(String id, UpdateRestaurantRequest request) {

        Restaurants restaurants = restaurantRepository
                .findById(id)
                .orElseThrow(RestaurantNotFoundException::new);

        restaurants.setName(request.name());
        restaurants.setDescription(request.description());
        restaurants.setRestaurantCategories(request.restaurantCategories());
        restaurants.setPlaceType(request.placeType());
        restaurants.setMainPoints(request.mainPoints());
        restaurants.setWebsiteLink(request.websiteLink());
        restaurants.setContacts(request.contacts());
        restaurants.setSocialMedia(request.socialMedia());
        restaurants.setOpenTime(request.openTime());
        restaurants.setClosedTime(request.closedTime());
        restaurants.setCity(request.city());
        restaurants.setLocation(request.location());
        Restaurants save = restaurantRepository.save(restaurants);
        return restaurantConverter.entityToRestaurantResponse(save);

    }

    public String deleteRestaurant(String id) {
        Restaurants restaurants = restaurantRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);
        restaurantRepository.delete(restaurants);
        return "Restaurant Successfully Deleted";
    }


    public RestaurantResponseForUser getRestaurantByOwnerId(String id) {
        Restaurants restaurants = restaurantRepository
                .findRestaurantsByOwnerId(id)
                .orElseThrow(RestaurantNotFoundException::new);
        return restaurantConverter.entityToResponse(restaurants);

    }

}
