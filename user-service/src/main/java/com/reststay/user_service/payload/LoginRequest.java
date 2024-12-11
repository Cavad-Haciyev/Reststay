package com.reststay.user_service.payload;


public record LoginRequest (
    String email,
    String password
    ){
}
