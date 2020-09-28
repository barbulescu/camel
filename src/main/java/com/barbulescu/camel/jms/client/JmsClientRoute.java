package com.barbulescu.camel.jms.client;

import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.apache.camel.ExchangePattern.InOnly;
import static org.apache.camel.ExchangePattern.InOut;
import static org.apache.camel.builder.endpoint.dsl.ActiveMQEndpointBuilderFactory.ActiveMQEndpointBuilder;

@Component
public class JmsClientRoute extends EndpointRouteBuilder {
    @Override
    public void configure() {
        ActiveMQEndpointBuilder endpointBuilder = activemq("{{input.queue}}");
        endpointBuilder.replyTo("{{aggregation.queue}}");
        endpointBuilder.timeToLive(1000);

        from(file("/tmp/input"))
                .convertBodyTo(String.class)
                .bean(TrimBean.class)
                .setHeader("JMSCorrelationID", UUID::randomUUID)
                .setProperty("PROPERTY_X1", () -> "P1")
                .setHeader("HEADER_X1", () -> "H1")
                .bean(new LoggerBean("client before"))
                .to(InOut, endpointBuilder)
                .bean(new LoggerBean("client after"))
                .bean(ResponseProcessor.class);

        from(activemq("{{output.queue}}"))
                .bean(new LoggerBean("aggregation"))
                .aggregate(header("JMSCorrelationID"), AggregationStrategies.bean(MyBodyAppender.class))
                .completionPredicate(exchange -> "true".equals(exchange.getProperty("aggregationDone")))
                .completionTimeout(3_000)
                .to(InOnly, activemq("{{aggregation.queue}}"))
        ;

    }
}
