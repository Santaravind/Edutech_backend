package com.example.JwtAuth.repositery;

import com.example.JwtAuth.mode.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepositery extends JpaRepository<Notification,Long> {
}
