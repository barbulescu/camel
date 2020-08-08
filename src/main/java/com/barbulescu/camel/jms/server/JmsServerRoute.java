package com.barbulescu.camel.jms.server;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.Exchange.CORRELATION_ID;

@Component
public class JmsServerRoute extends EndpointRouteBuilder {
    @Override
    public void configure() {
        from(activemq("{{input.queue}}").advanced().useMessageIDAsCorrelationID(true))
                .log("[${header:" + CORRELATION_ID + "}] Received a message - ${body}")
                .bean(ProcessMessage.class)
                .to(activemq("{{output.queue}}").advanced().useMessageIDAsCorrelationID(true));
    }
}
