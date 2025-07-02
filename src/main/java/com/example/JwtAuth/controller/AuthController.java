package com.example.JwtAuth.controller;

import com.example.JwtAuth.dto.LoginUserDto;
import com.example.JwtAuth.dto.RegisteruserDto;
import com.example.JwtAuth.dto.VerifyUserDto;
import com.example.JwtAuth.mode.UserEntity;
import com.example.JwtAuth.response.LoginResponse;
import com.example.JwtAuth.service.AuthService;
import com.example.JwtAuth.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisteruserDto registeruserDto){
        UserEntity user = new UserEntity();
        user.setEmail(registeruserDto.getEmail()); // <- THIS IS MANDATORY
        UserEntity registeruser=authService.signUp(registeruserDto);
        return  ResponseEntity.ok(registeruser);

    }




    @PostMapping( value="/login", produces = "application/json")
    public ResponseEntity<LoginResponse> authentication(@RequestBody LoginUserDto loginUserDto) {
        UserEntity user = authService.authentication(loginUserDto);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token, jwtService.getExpirationTime()));
    }


    @PostMapping("/verify")
    @Operation(description = "verify your email after register")
    public  ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto){
        try{
            authService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully !!!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public ResponseEntity<?> resendVerifyCationCode(@RequestBody  String email){
        try{
            authService.resendVerificationCode(email);
            return ResponseEntity.ok("verification code sent !!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
