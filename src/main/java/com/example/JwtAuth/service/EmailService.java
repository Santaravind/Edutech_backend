package com.example.JwtAuth.service;


import com.example.JwtAuth.mode.UserEntity;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${spring.mail}")
    private String fromEmail;

public void sendVerificationEmail(String toEmail,String subject ,String text) throws IOException {
    Email from = new Email(fromEmail);
    Email to = new Email(toEmail);
    Content content = new Content("text/html",text);
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(sendGridApiKey);
    Request request = new Request();

    try {
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);

        System.out.println("Email sent! Status: " + response.getStatusCode());
    } catch (IOException ex) {
        System.out.println(ex);
    }


}

}
