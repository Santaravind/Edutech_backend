package com.example.JwtAuth.controller.webSocket;

import com.example.JwtAuth.mode.Notification;
import com.example.JwtAuth.service.webSocket.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    public NotificationService service;


    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getall() {
        List<Notification> list = service.getAllNotification();
        return ResponseEntity.ok(list);   // ✅ Proper response
    }
}
