package com.example.JwtAuth.service;

import com.example.JwtAuth.mode.UserEntity;
import com.example.JwtAuth.repositery.UserRepository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class serviceUser {
    @Autowired
    private UserRepository userRepository;

    public serviceUser(UserRepository userRepository,EmailService emailService ){
        this.userRepository=userRepository;
    }

    public List<UserEntity> getAllUser() {
        List<UserEntity> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

}

