package com.reststay.restaurant_service.dto;

import com.reststay.restaurant_service.model.City;
import com.reststay.restaurant_service.model.MainPoints;
import com.reststay.restaurant_service.model.PlaceType;
import com.reststay.restaurant_service.model.RestaurantCategory;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;
@Builder
public record UpdateRestaurantRequest(
        String name,
        String description,
        List<RestaurantCategory> restaurantCategories,
        PlaceType placeType,
        List<MainPoints> mainPoints,
        String websiteLink,
        List<String> contacts,
        List<String> socialMedia,
        LocalTime openTime,
        LocalTime closedTime,
        City city,
        String location

) {
}
