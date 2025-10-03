//package com.example.JwtAuth.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class EmailConfigation {
//
//    @Value("${spring.mail.username}")
//    private String email;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    // this for tls
////    @Bean
////    public JavaMailSender javaMailSender(){
////        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
////        mailSender.setHost("smtp.gmail.com");
////        mailSender.setPort(587);
////        mailSender.setUsername(email);
////        mailSender.setPassword(password);
////
////        Properties props=mailSender.getJavaMailProperties();
////        props.put("mail.transport.protocol","smtp");
////        props.put("mail.smtp.auth","true");
////        props.put("mail.smtp.starttls.enable","true");
////        props.put("mail.debug","true");
////
////        return mailSender;
////
////    }
//
//@Bean
//    public JavaMailSender javaMailSender(){
//        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
//
//    mailSender.setHost("smtp.gmail.com");
//    mailSender.setPort(465);
//    mailSender.setUsername( email);
//    mailSender.setPassword(password);
//
//    Properties props = mailSender.getJavaMailProperties();
//    props.put("mail.transport.protocol", "smtps");
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.ssl.enable", "true");
//    props.put("mail.smtp.socketFactory.port", "465");
//    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//    props.put("mail.smtp.connectiontimeout", "5000");
//    props.put("mail.smtp.timeout", "5000");
//    props.put("mail.smtp.writetimeout", "5000");
//
//
//    return mailSender;
//
//    }
//
//
//}
