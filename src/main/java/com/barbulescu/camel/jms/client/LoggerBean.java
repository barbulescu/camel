package com.barbulescu.camel.jms.client;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Handler;
import org.apache.camel.Headers;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public class LoggerBean {

    private final String context;

    public LoggerBean(String context) {
        this.context = context;
    }

    @Handler
    void log(@Body Object body, @Headers Map<String, Object> headers, @ExchangeProperties Map<String, Object> properties) {
        String headerLine = headers.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .map(it -> "\n\t" + it)
                .collect(joining(""));
        String propertiesLine = properties.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .map(it -> "\n\t" + it)
                .collect(joining(""));

        System.out.println(">>>--------------- " + context + " ------------------------");
        System.out.println("headers: " + headerLine);
        System.out.println("properties: " + propertiesLine);
        System.out.println("body: >" + body + "< of type " + body.getClass().getSimpleName());
        System.out.println("<<<---------------------------------------");
    }
}
