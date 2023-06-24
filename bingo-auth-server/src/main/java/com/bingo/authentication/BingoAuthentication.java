package com.bingo.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BingoAuthentication {
    public static void main(String[] args) {
        SpringApplication.run(BingoAuthentication.class, args);
    }
}
