package com.reststay.restaurant_service.dto.converter;

import com.reststay.restaurant_service.dto.*;
import com.reststay.restaurant_service.model.Restaurants;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Builder
@RequiredArgsConstructor
@Component
public class RestaurantConverter {

    public Restaurants requestToEntity(CreateRestaurantRequest request){

        return Restaurants.builder()
                .name(request.getName())
                .description(request.getDescription())
                .restaurantCategories(request.getRestaurantCategories())
                .placeType(request.getPlaceType())
                .mainPoints(request.getMainPoints())
                .websiteLink(request.getWebsiteLink())
                .socialMedia(request.getSocialMedia())
                .contacts(request.getContacts())
                .openTime(request.getOpenTime())
                .closedTime(request.getClosedTime())
                .city(request.getCity())
                .location(request.getLocation())
                .build();
    }
    public RestaurantResponseForUser entityToResponse(Restaurants request){
        return RestaurantResponseForUser.builder()
                .name(request.getName())
                .description(request.getDescription())
                .restaurantCategories(request.getRestaurantCategories())
                .placeType(request.getPlaceType())
                .mainPoints(request.getMainPoints())
                .websiteLink(request.getWebsiteLink())
                .contacts(request.getContacts())
                .socialMedia(request.getSocialMedia())
                .openTime(request.getOpenTime())
                .closedTime(request.getClosedTime())
                .city(request.getCity())
                .location(request.getLocation())
                .reviews(request.getReviews())
                .build();
    }
    public RestaurantResponse entityToRestaurantResponse(Restaurants request){
        return RestaurantResponse.builder()
                .name(request.getName())
                .description(request.getDescription())
                .restaurantCategories(request.getRestaurantCategories())
                .placeType(request.getPlaceType())
                .mainPoints(request.getMainPoints())
                .websiteLink(request.getWebsiteLink())
                .contacts(request.getContacts())
                .socialMedia(request.getSocialMedia())
                .openTime(request.getOpenTime())
                .closedTime(request.getClosedTime())
                .city(request.getCity())
                .location(request.getLocation())
                .reviews(request.getReviews())

                .build();
    }

}
