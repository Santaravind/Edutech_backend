package com.example.JwtAuth.service;

import com.example.JwtAuth.dto.LoginUserDto;
import com.example.JwtAuth.dto.RegisteruserDto;
import com.example.JwtAuth.dto.VerifyUserDto;
import com.example.JwtAuth.mode.UserEntity;
import com.example.JwtAuth.repositery.UserRepository;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

//
//    public UserEntity signUp(RegisteruserDto input){
//        UserEntity user=new UserEntity(
//                input.getFirstName(),
//                input.getMiddleName(),
//                input.getLastName(),
//                input.getFatherName(),
//                input.getDOB(),
//                input.getEmail(),
//                input.getAadharNo(),
//                input.getMobileNo(),
//                encoder.encode(input.getPassword()));
//              user.setVerificationCode(generateVerificationCode());
//              user.setVerificationCodeExporesAt(LocalDateTime.now().plusMinutes(15));
//              user.setEnable(false);
//        sendVerificationEmail(user);
//              return repository.save(user);
//    }

    public UserEntity signUp(RegisteruserDto input) {
        UserEntity user = new UserEntity();
        user.setFirstName(input.getFirstName());
        user.setMiddleName(input.getMiddleName());
        user.setLastName(input.getLastName());
        user.setFatherName(input.getFatherName());
      //  user.setDOB(input.getDOB());
        user.setEmail(input.getEmail()); // ✅ important
        user.setAadharNo(input.getAadharNo());
        user.setMobileNo(input.getMobileNo());
        user.setPassword(encoder.encode(input.getPassword()));
        user.setEnable(false);
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExporesAt(LocalDateTime.now().plusMinutes(15));

        sendVerificationEmail(user);
        return repository.save(user);
    }
//public UserEntity signUp(RegisteruserDto input){
//    UserEntity user = new UserEntity();
//    user.setFirstName(input.getFirstName());
//    user.setMiddleName(input.getMiddleName());
//    user.setLastName(input.getLastName());
//    user.setFatherName(input.getFatherName());
//    user.setDOB(input.getDOB()); // ✅ This is critical
//    user.setEmail(input.getEmail());
//    user.setAadharNo(input.getAadharNo());
//    user.setMobileNo(input.getMobileNo());
//    user.setPassword(encoder.encode(input.getPassword()));
//    user.setVerificationCode(generateVerificationCode());
//    user.setVerificationCodeExporesAt(LocalDateTime.now().plusMinutes(15));
//    user.setEnable(false);
//
//    sendVerificationEmail(user);
//    return repository.save(user); // ✅ Now DOB is NOT NULL
//}



    public UserEntity authentication(LoginUserDto inputLog){
        UserEntity user = repository.findByEmail(inputLog.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.isEnable()){
            throw new RuntimeException("Account not verified . Please verify your account!!");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        inputLog.getEmail(),
                        inputLog.getPassword()
                )
        );

        return  user;
    }


    public void verifyUser(VerifyUserDto input) {
        Optional<UserEntity> optionaluser = repository.findByEmail(input.getEmail());
        if (optionaluser.isPresent()) {
            UserEntity user1 = optionaluser.get();
            if (user1.getVerificationCodeExporesAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("verification code has expired");
         }
            if (user1.getVerificationCode().equals(input.getVerificationCode())) {
                user1.setEnable(true);
                user1.setVerificationCode(null);
                user1.setVerificationCodeExporesAt(null);

                repository.save(user1);
            } else {
                throw new RuntimeException("Invalid verification code");
            }
        } else {
            throw new RuntimeException("User not found");
        }

    }

    public void resendVerificationCode(String email){
        Optional<UserEntity> optionalUser=repository.findByEmail(email);
        if (optionalUser.isPresent()){
            UserEntity user=optionalUser.get();
            if (user.isEnable()){
                throw new RuntimeException("Acoound is already verified");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExporesAt(LocalDateTime.now().plusMinutes(15));
            sendVerificationEmail(user);
            repository.save(user);
        }else {
            throw  new RuntimeException("User not found");
        }
    }

    private void sendVerificationEmail(UserEntity user) { //TODO: Update with company logo
        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to Happy digital Bharat!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            //for check the email sender
            if (user.getEmail() == null || user.getEmail().isBlank()) {
                throw new IllegalArgumentException("User email must not be null or empty");
            }

            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            System.out.println("Email not send "+e);
            // Handle email sending exception
            e.printStackTrace();
        }
    }
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

}
