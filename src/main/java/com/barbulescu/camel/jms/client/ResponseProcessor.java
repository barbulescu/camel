package com.barbulescu.camel.jms.client;

import org.apache.camel.Body;
import org.apache.camel.Handler;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class ResponseProcessor {

    @Handler
    public void process(@Body List<String> body) {
        String message = body.stream().collect(joining(" "));
        System.out.println(">>> Received '" + message + "'");
    }
}
