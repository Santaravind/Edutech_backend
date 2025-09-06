package com.example.JwtAuth.service.webSocket;

import com.example.JwtAuth.repositery.NotificationRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class  ClearUpSpace{

    @Autowired
    public NotificationRepositery repositery;

    // Run every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteOldNotifications() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        repositery.findAll().forEach(n -> {
            if (n.getCreateAt().isBefore(sevenDaysAgo)) {
                repositery.delete(n);
            }
        });
    }





        }
