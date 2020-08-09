package com.barbulescu.camel.jms.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;

import javax.jms.TextMessage;

import static org.springframework.jms.support.JmsHeaders.*;

@SpringBootApplication
public class JmsServer {

    private final JmsTemplate jmsTemplate;

    public JmsServer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(JmsServer.class)
                .run(args);
    }

    @JmsListener(destination = "jms:requestQueue")
    public void listen(
            String payload,
            @Header(CORRELATION_ID) String correlationId) {
        System.out.println(payload + " - " + correlationId);
        String response = "Server says hello " + payload;
        jmsTemplate.send("jms:responseQueue", session -> {
            TextMessage message = session.createTextMessage(response);
            message.setJMSCorrelationID(correlationId);
            return message;
        });
    }
}