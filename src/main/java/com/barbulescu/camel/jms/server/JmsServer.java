package com.barbulescu.camel.jms.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;

import javax.jms.TextMessage;
import java.util.Random;

import static org.springframework.jms.support.JmsHeaders.CORRELATION_ID;

@SpringBootApplication
public class JmsServer {

    private final JmsTemplate jmsTemplate;
    private final Random random = new Random(System.currentTimeMillis());

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
        System.out.println("IN> " + correlationId + " - " + payload);

        if (random.nextBoolean()) {
            sendResponse(correlationId, "Server says hello");
            sendResponse(correlationId, payload);
        } else {
            sendResponse(correlationId, "Server says hello " + payload);
        }
        sendResponse(correlationId, "END");
    }

    private void sendResponse(String correlationId, String response) {
        System.out.println("OUT> " + correlationId + " - " + response);
        jmsTemplate.send("jms:responseQueue", session -> {
            TextMessage message = session.createTextMessage(response);
            message.setJMSCorrelationID(correlationId);
            return message;
        });
    }
}