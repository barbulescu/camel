package com.barbulescu.camel.jms.client;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.apache.camel.Exchange.CORRELATION_ID;

@Component
public class JmsClientRoute extends EndpointRouteBuilder {
    @Override
    public void configure() {
        from(timer("foo").period(20_000))
                .setBody(constant("Marius"))
                .setHeader(CORRELATION_ID, () -> UUID.randomUUID().toString())
                .to(activemq("{{input.queue}}").advanced().useMessageIDAsCorrelationID(true))
                .log("Sent message ${body} ${header:" + CORRELATION_ID + "}");

        from(activemq("{{output.queue}}").advanced().useMessageIDAsCorrelationID(true))
                .bean(ResponseProcessor.class);
    }
}
