package com.reststay.restaurant_service.dto;

import com.reststay.restaurant_service.model.City;
import com.reststay.restaurant_service.model.MainPoints;
import com.reststay.restaurant_service.model.PlaceType;
import com.reststay.restaurant_service.model.RestaurantCategory;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private List<RestaurantCategory> restaurantCategories;
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;
    @Enumerated(EnumType.STRING)
    private List<MainPoints> mainPoints;
    private String websiteLink;
    @ElementCollection
    private List<String> contacts;
    @ElementCollection
    private List<String> socialMedia;
    private LocalTime closedTime;
    private LocalTime openTime;
    private City city;
    private String location;
    @ElementCollection
    private List<String> reviews;
    private UserResponse owner;

}
