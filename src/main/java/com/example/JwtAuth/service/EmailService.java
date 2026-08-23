package com.example.JwtAuth.service;


//import com.example.JwtAuth.mode.UserEntity;
//import com.sendgrid.Method;
//import com.sendgrid.Request;
//import com.sendgrid.Response;
//import com.sendgrid.SendGrid;
//import com.sendgrid.helpers.mail.Mail;
//import com.sendgrid.helpers.mail.objects.Content;
//import com.sendgrid.helpers.mail.objects.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
//public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;

//    @Value("${sendgrid.api.key}")
//    private String sendGridApiKey;
//
//    @Value("${spring.mail}")
//    private String fromEmail;
//
//public void sendVerificationEmail(String toEmail,String subject ,String text) throws IOException {
//    Email from = new Email(fromEmail);
//    Email to = new Email(toEmail);
//    Content content = new Content("text/html",text);
//    Mail mail = new Mail(from, subject, to, content);
//
//    SendGrid sg = new SendGrid(sendGridApiKey);
//    Request request = new Request();
//
//    try {
//        request.setMethod(Method.POST);
//        request.setEndpoint("mail/send");
//        request.setBody(mail.build());
//        Response response = sg.api(request);
//
//        System.out.println("Email sent! Status: " + response.getStatusCode());
//    } catch (IOException ex) {
//        System.out.println(ex);
//    }
//
//
//}

//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendVerificationEmail(String toEmail, String subject, String text) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//        helper.setFrom("${spring.mail.username}"); // Use the same email configured in properties
//        helper.setTo(toEmail);
//        helper.setSubject(subject);
//        helper.setText(text, true); // 'true' enables HTML content
//
//        mailSender.send(message);
//    }
//}


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendVerificationEmail(String toEmail, String subject, String htmlBody) {
        String url = "https://api.resend.com/emails";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("from", "Acme <onboarding@resend.dev>");
        requestBody.put("from", "Happy Digital Bharat<no-reply@aravindsant.in>");
        requestBody.put("to", new String[]{toEmail});
//        requestBody.put("to", new String[]{"aravindsant1310@gmail.com"});
        requestBody.put("subject", subject);
        requestBody.put("html", htmlBody);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);
            System.out.println("Email sent! ID: " + response.get("id"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }
}