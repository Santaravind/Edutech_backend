package com.example.JwtAuth.dto;

import java.time.LocalDateTime;

public class Notificationdto {
    private String title;
    private String message;
    private String link;

    private LocalDateTime creactAt;

    public LocalDateTime getCreactAt() {
        return creactAt;
    }

    public void setCreactAt(LocalDateTime creactAt) {
        this.creactAt = creactAt;
    }

    public Notificationdto(String link, String message, String title) {
        this.link = link;
        this.message = message;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
