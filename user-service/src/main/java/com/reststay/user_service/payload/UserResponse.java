package com.reststay.user_service.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class UserResponse {
    private String username;
    private String email;
    private String phoneNumber;

}
