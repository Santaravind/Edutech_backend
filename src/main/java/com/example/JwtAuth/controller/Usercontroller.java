package com.example.JwtAuth.controller;

import com.example.JwtAuth.mode.UserEntity;
import com.example.JwtAuth.service.serviceUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@Tag(name = "Users APIs " ,description = "getAll")
public class Usercontroller {

    @Autowired
    private serviceUser  userService;



    @GetMapping("/allUser")
    public ResponseEntity<List<UserEntity>> allUsers() {
        List<UserEntity> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
