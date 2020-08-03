package com.barbulescu.camel;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProcessorTypesRouteBuilder extends EndpointRouteBuilder {

    @Override
    public void configure() {
        from(direct("processor"))
                .routeId("processor")
                .process(new SimpleProcessor());
    }
}
