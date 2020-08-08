package com.barbulescu.camel.jms.server;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.ExchangePattern.InOut;

@Component
public class JmsServerRoute extends EndpointRouteBuilder {
    @Override
    public void configure() {
        from(activemq("{{input.queue}}").replyTo("{{output.queue}}")
                .advanced()
                .exchangePattern(InOut))
                .bean(ProcessMessage.class);
    }
}
