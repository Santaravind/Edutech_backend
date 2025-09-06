package com.example.JwtAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSecurity
@EnableWebSocket
@ComponentScan(basePackages = {"com.example.JwtAuth"})
public class JwtAuthApplication {

	public static void main(String[] args) {

		SpringApplication.run(JwtAuthApplication.class, args);
	}

}
