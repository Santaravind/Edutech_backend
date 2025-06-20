package com.example.JwtAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity

public class JwtAuthApplication {

	public static void main(String[] args) {

		SpringApplication.run(JwtAuthApplication.class, args);
	}

}
