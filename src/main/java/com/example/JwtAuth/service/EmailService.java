package com.example.JwtAuth.service;


import com.example.JwtAuth.mode.UserEntity;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to,String subject,String text) throws MessagingException {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text,true);

        mailSender.send(message);
    }
//private void sendVerificationEmail(UserEntity user) {
//    if (user.getEmail() == null || user.getEmail().isBlank()) {
//        throw new IllegalArgumentException("Email is missing for user: " + user.getUsername());
//    }
//
//    String subject = "Account Verification";
//    String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
//    String htmlMessage = "<html>... your template ...</html>";
//
//    try {
//        mailSender.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
//    } catch (MessagingException e) {
//        System.out.println("Email not sent: " + e.getMessage());
//        e.printStackTrace();
//    }
//}

}
