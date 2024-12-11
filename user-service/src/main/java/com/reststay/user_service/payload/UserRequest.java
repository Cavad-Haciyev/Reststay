package com.reststay.user_service.payload;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;

@Builder
public record UserRequest (

        String username,
        @Email(message = "Email should be valid")
//        @UniqueElements(message = "Email already defined")
        @Column(unique = true ,columnDefinition = "Email already defined")
        String email,
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[\\S]{8,}$",
                message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
        )
        String password,
        @Pattern(
                regexp = "^(\\+994|0)\\s?(50|51|55|70|77)\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}$",
                message = "Invalid Azerbaijan phone number")
        String phoneNumber

){
}
