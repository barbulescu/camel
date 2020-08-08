package com.barbulescu.camel.jms.client;

import org.apache.camel.builder.EndpointProducerBuilder;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.ExchangePattern.InOut;

@Component
public class JmsClientRoute extends EndpointRouteBuilder {
    @Override
    public void configure() {
        from(timer("foo").period(20_000))
                .setBody(constant("Marius"))
                .to(InOut, (EndpointProducerBuilder) activemq("{{input.queue}}").replyTo("{{output.queue}}"))
                .bean(ResponseProcessor.class);

    }
}
