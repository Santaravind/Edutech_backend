package com.example.JwtAuth.service.webSocket;

import com.example.JwtAuth.dto.Notificationdto;
import com.example.JwtAuth.mode.Notification;
import com.example.JwtAuth.repositery.NotificationRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    public NotificationRepositery repositery;


    public Notification sendNotification(Notificationdto dto){
        Notification entity=new Notification();
        entity.setTitle(dto.getTitle());
        entity.setMessage(dto.getMessage());
        entity.setLink(dto.getLink());
        entity.setCreateAt(LocalDateTime.now());
         return repositery.save(entity);

    }

    public List<Notification> getAllNotification() {

           List<Notification> list = repositery.findAll();

            if (list.isEmpty()) {
                System.out.println("No data found in notifications.");
            } else {
                System.out.println("Fetched notifications: " + list.size());
                list.forEach(n -> System.out.println(n.getMessage()));
            }

            return repositery.findAll();
        }

//
//    public List<Notification> getAllNotification() {
//        return repositery.findAll(); // ✅ should query "notification"
//    }



}
