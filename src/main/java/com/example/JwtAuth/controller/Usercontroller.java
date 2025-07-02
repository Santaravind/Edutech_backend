package com.example.JwtAuth.controller;

import com.example.JwtAuth.mode.UserEntity;
import com.example.JwtAuth.service.serviceUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users APIs " ,description = "getAll")
public class Usercontroller {

    @Autowired
    private serviceUser  userService;

//    @GetMapping("/me")
//    public ResponseEntity<UserEntity> authenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
//        return ResponseEntity.ok(currentUser);
//    }


    @GetMapping("/allUser")
    public ResponseEntity<List<UserEntity>> allUsers() {
        List<UserEntity> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
