package com.reststay.user_service.payload.converter;

import com.reststay.user_service.model.User;
import com.reststay.user_service.payload.UserResponse;
import com.reststay.user_service.payload.UserRestaurantResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@RequiredArgsConstructor
@Component
public class UserConverter {

    public UserResponse entityToResponse(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
    public UserRestaurantResponse entityUserRestaurant(User user){
        return UserRestaurantResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
