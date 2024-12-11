package com.reststay.restaurant_service.dto.converter;

import com.reststay.restaurant_service.model.MainPoints;
import com.reststay.restaurant_service.model.RestaurantCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class RestaurantCategoryConverter implements AttributeConverter<List<RestaurantCategory>, String> {
    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<RestaurantCategory> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public List<RestaurantCategory> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        return Arrays.stream(dbData.split(SEPARATOR))
                .map(RestaurantCategory::valueOf)
                .collect(Collectors.toList());
    }
}
