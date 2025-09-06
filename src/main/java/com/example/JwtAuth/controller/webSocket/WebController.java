package com.example.JwtAuth.controller.webSocket;

import com.example.JwtAuth.dto.Notificationdto;
import com.example.JwtAuth.service.webSocket.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Controller
public class WebController {
    @Autowired
    public NotificationService service;

    @MessageMapping("/sendNotification")
    @SendTo("/topic/notifications")
    public Notificationdto sendNotification(@Payload Notificationdto dto){

        service.sendNotification(dto);

        return dto;

    }

}
