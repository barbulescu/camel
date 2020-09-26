package com.barbulescu.camel.jms.client;

import org.apache.camel.Body;
import org.apache.camel.Handler;

public class ResponseProcessor {
    @Handler
    public void process(@Body String body) {
        System.out.println(">>> Received '" + body + "'");
    }
}
