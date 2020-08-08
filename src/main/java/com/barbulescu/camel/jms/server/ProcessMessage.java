package com.barbulescu.camel.jms.server;

import org.apache.camel.Body;
import org.apache.camel.Handler;

public class ProcessMessage {
    @Handler
    public String process(@Body String body) {
        return "Server says - hello " + body;
    }
}
