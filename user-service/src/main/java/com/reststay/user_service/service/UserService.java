package com.reststay.user_service.service;

import com.reststay.restaurant_service.dto.RestaurantResponseForUser;
import com.reststay.user_service.client.RestaurantServiceClient;
import com.reststay.user_service.config.JwtUtil;
import com.reststay.user_service.config.email.EmailService;
import com.reststay.user_service.exception.UserNotFoundException;
import com.reststay.user_service.model.Role;
import com.reststay.user_service.model.User;
import com.reststay.user_service.payload.UserRequest;
import com.reststay.user_service.payload.UserResponse;
import com.reststay.user_service.payload.UserRestaurantResponse;
import com.reststay.user_service.payload.converter.UserConverter;
import com.reststay.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final RoleService roleService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final UserConverter userConverter;
    private final RestaurantServiceClient restaurantServiceClient;

    public UserService(UserRepository repository,
                       RoleService roleService, EmailService emailService, JwtUtil jwtUtil, UserConverter userConverter, RestaurantServiceClient restaurantServiceClient) {
        this.repository = repository;

        this.roleService = roleService;
        this.emailService = emailService;
        this.jwtUtil = jwtUtil;
        this.userConverter = userConverter;
        this.restaurantServiceClient = restaurantServiceClient;
    }

    public UserResponse getUserById(String userId){
        User user = repository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return userConverter.entityToResponse(user);
    }

    public UserRestaurantResponse getUserRestaurant(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);;
        RestaurantResponseForUser restaurant = restaurantServiceClient.getRestaurantById(user.getId()).getBody();
        UserRestaurantResponse userRestaurantResponse = userConverter.entityUserRestaurant(user);
        userRestaurantResponse.getRestaurants().add(restaurant);
        return userRestaurantResponse;


    }



public void addRestaurantToUser(String userId, String  restaurantId) {
    User user = repository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

    List<String> restaurants = user.getRestaurants();
    if (!restaurants.contains(restaurantId)) {
        restaurants.add(restaurantId);
    }

    user.setRestaurants(restaurants);
    repository.save(user);
}








    public String registration(UserRequest request) {
        Role roleById = roleService.getRoleById(2L);

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setPhoneNumber(request.phoneNumber());
        user.setActivate(false);
        user.setVisible(true);
        user.setRoles(List.of(roleById));
        repository.save(user);
        String link = "http://localhost:8081/api/user/activate/" + user.getEmail();
        emailService.sendSimpleEmail(user.getEmail(),
                link,
                "Activate Link");
        return "User Successfully Registration";
    }


    public String activateProfile(String email) {
        User user = repository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        user.setActivate(true);
        repository.save(user);
        return "Successfully Activate Profile";
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email));
    }


//    LOGIN

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    public String validateToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7); // "Bearer " çıxarılır
            return jwtUtil.getUserIdFromToken(jwt);
        } else {
            throw new UserNotFoundException();
        }

    }
}
