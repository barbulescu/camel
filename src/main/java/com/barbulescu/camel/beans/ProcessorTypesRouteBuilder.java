package com.barbulescu.camel.beans;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("beans")
public class ProcessorTypesRouteBuilder extends EndpointRouteBuilder {

    @Override
    public void configure() {
        from(direct("processorIn"))
                .routeId("processorRoute")
                .process(new SimpleProcessor())
                .to(direct("processorOut")
                        .block(false)
                        .failIfNoConsumers(false));
    }
}
