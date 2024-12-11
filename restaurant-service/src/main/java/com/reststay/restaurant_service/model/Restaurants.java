package com.reststay.restaurant_service.model;

import com.reststay.restaurant_service.dto.converter.MainPointConverter;
import com.reststay.restaurant_service.dto.converter.RestaurantCategoryConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurant")
public class Restaurants {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String description;
    @Convert(converter = RestaurantCategoryConverter.class)
    private List<RestaurantCategory> restaurantCategories;
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @Convert(converter = MainPointConverter.class)
    private List<MainPoints> mainPoints;
    private String websiteLink;
    @ElementCollection
    private List<String> contacts;
    @ElementCollection
    private List<String> socialMedia;
    private LocalTime openTime;
    private LocalTime closedTime;
    @Enumerated(EnumType.STRING)
    private City city;
    private String location;
    @ElementCollection
    private List<String> reviews;
    private String ownerId;

}
