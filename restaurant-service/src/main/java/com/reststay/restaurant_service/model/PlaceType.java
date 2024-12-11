package com.reststay.restaurant_service.model;

public enum PlaceType {
    PUB("Pub"),
    KARAOKE("Karaoke"),
    RESTORAN("Restoran"),
    WEDDING("Sadliq Sarayi");


    private final String displayName;

    // Constructor
    PlaceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
