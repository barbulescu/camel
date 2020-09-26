package com.barbulescu.camel.jms.client;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Handler;
import org.apache.camel.Headers;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public class LoggerBean {

    @Handler
    void log(@Body String body, @Headers Map<String, Object> headers, @ExchangeProperties Map<String, Object> properties) {
        System.out.println("headers: \n" + headers.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(joining("\n")));
        System.out.println("properties: \n" + properties.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(joining("\n")));
    }
}
