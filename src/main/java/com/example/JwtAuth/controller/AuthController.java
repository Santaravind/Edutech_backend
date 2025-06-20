package com.example.JwtAuth.controller;

import com.example.JwtAuth.dto.LoginUserDto;
import com.example.JwtAuth.dto.RegisteruserDto;
import com.example.JwtAuth.dto.VerifyUserDto;
import com.example.JwtAuth.mode.UserEntity;
import com.example.JwtAuth.response.LoginResponse;
import com.example.JwtAuth.service.AuthService;
import com.example.JwtAuth.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        //user.setDOB(registeruserDto.getDOB());

        UserEntity registeruser=authService.signUp(registeruserDto);
        return  ResponseEntity.ok(registeruser);

    }

//    @PostMapping("/login")
//   // @RequestBody
//    public ResponseEntity<?> authentication(@RequestBody LoginUserDto loginUserDto){
//        UserEntity authenticatedUSer=authService.authentication(loginUserDto);
//        String jwtToken=jwtService.generateToken(authenticatedUSer);
//        LoginResponse loginResponse=new LoginResponse(jwtToken, jwtService.getExpirationTime());
//        return ResponseEntity.ok(loginResponse);
//    }


    @PostMapping( value="/login", produces = "application/json")
    public ResponseEntity<LoginResponse> authentication(@RequestBody LoginUserDto loginUserDto) {
        UserEntity user = authService.authentication(loginUserDto);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token, jwtService.getExpirationTime()));
    }


    @PostMapping("/verify")
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
