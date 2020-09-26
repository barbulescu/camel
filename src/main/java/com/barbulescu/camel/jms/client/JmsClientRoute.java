package com.barbulescu.camel.jms.client;

import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.apache.camel.ExchangePattern.InOnly;

@Component
public class JmsClientRoute extends EndpointRouteBuilder {
    @Override
    public void configure() {
        from(timer("foo").period(5_000))
                .setBody(constant("Marius"))
                .setHeader("JMSCorrelationID", UUID::randomUUID)
                .log("set correlationId: ${header.JMSCorrelationID}")
                .to(InOnly, activemq("{{input.queue}}")
                        .timeToLive(1_000));

        from(activemq("{{output.queue}}"))
                .log("received correlationId: ${header.JMSCorrelationID}")
                .bean(LoggerBean.class)
                .aggregate(header("JMSCorrelationID"), AggregationStrategies.bean(MyBodyAppender.class))
                .completionSize(2)
                .completionTimeout(3_000)
                .bean(ResponseProcessor.class);

    }
}
