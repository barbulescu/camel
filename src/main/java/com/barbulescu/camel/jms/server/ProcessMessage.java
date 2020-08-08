package com.barbulescu.camel.jms.server;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;

import static org.apache.camel.Exchange.CORRELATION_ID;

public class ProcessMessage {
    @Handler
    public String process(@Body String body, @Header(CORRELATION_ID) String correlationId) {
        return "[" + correlationId + "] Hello " + body;
    }
}
