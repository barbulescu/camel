package com.barbulescu.camel.jms.client;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;

import static org.apache.camel.Exchange.CORRELATION_ID;

public class ResponseProcessor {
    @Handler
    public void process(@Body String body, @Header(CORRELATION_ID) String correlationId) {
        System.out.println("[" + correlationId + "] Received " + body);
    }
}
