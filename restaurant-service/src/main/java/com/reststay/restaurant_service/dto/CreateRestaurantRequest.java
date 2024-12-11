package com.reststay.restaurant_service.dto;

import com.reststay.restaurant_service.model.City;
import com.reststay.restaurant_service.model.MainPoints;
import com.reststay.restaurant_service.model.PlaceType;
import com.reststay.restaurant_service.model.RestaurantCategory;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantRequest {
    private String id;
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
    private LocalTime openTime;
    private LocalTime closedTime;
    private City city;
    private String location;
    @ElementCollection
    private List<String> reviews;



}
