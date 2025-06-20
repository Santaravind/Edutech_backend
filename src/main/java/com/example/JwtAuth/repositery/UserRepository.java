package com.example.JwtAuth.repositery;

import com.example.JwtAuth.mode.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {


    Optional<UserEntity> findByEmail(String email);
    Optional<User> findByVerificationCode(String verificationCode);

}
