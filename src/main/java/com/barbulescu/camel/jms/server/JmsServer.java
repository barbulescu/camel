package com.barbulescu.camel.jms.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JmsServer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(JmsServer.class)
                .run(args);
    }
}