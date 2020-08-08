package com.barbulescu.camel.jms.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JmsClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(JmsClient.class)
                .run(args);
    }
}