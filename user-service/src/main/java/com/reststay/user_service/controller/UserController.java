package com.reststay.user_service.controller;

import com.reststay.user_service.baseresponse.ResponseModelService;
import com.reststay.user_service.config.JwtResponse;
import com.reststay.user_service.config.JwtUtil;
import com.reststay.user_service.model.User;
import com.reststay.user_service.payload.LoginRequest;
import com.reststay.user_service.payload.UserRequest;
import com.reststay.user_service.payload.UserResponse;
import com.reststay.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable  String id) {

        return ResponseEntity.ok(userService.getUserById(id));

    }

    @PutMapping("/{userId}/addRestaurant")
    public ResponseEntity<Void> addRestaurantToUser(@PathVariable String userId, @RequestBody String restaurantId) {
        userService.addRestaurantToUser(userId, restaurantId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody @Valid UserRequest userDto) {

        return ResponseModelService.responseBuilder(
                LocalDateTime.now(),
                userService.registration(userDto),
                "Successfully",
                HttpStatus.OK);

    }
    @GetMapping("/activate/{email}")
    public ResponseEntity<?> activateProfile(@PathVariable String email) {

        return ResponseModelService
                .responseBuilderaList(LocalDateTime.now(),
                        userService.activateProfile(email),
                        "Successfully",
                        HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(),
                            loginRequest.password()));

            log.info("User Qeydiyyatdan kecdi");


        } catch (UsernameNotFoundException usernameNotFoundException) {
            usernameNotFoundException.printStackTrace();
            log.info("User Qeydiyyatdan kecmedi");
            throw new Exception("Bad Credential");
        }

        // Kullanıcı detaylarını ve ID'yi yükleme
        UserDetails userDetails = this.userService.loadUserByUsername(loginRequest.email());
        User user = this.userService.findByEmail(loginRequest.email()); // userId'yi a
        String token = this.jwtUtil.generateToken(userDetails,user.getId());
        log.info("Token:  "+token);
        return ResponseEntity.ok(new JwtResponse(token));

    }


    @GetMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String token) {
        return userService.validateToken(token);
    }
}
