package com.reststay.restaurant_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public enum RestaurantCategory {
    ITALIAN("Italian"),
    MEXICAN("Mexican"),
    JAPANESE("Japanese"),
    FRENCH("French"),
    INDIAN("Indian"),
    AMERICAN("American"),
    CHINESE("Chinese"),
    MEDITERRANEAN("Mediterranean"),
    FAST_FOOD("Fast Food"),
    VEGAN("Vegan"),
    BBQ("BBQ"),
    SEAFOOD("Seafood");

    private final String displayName;

    // Constructor
    RestaurantCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
