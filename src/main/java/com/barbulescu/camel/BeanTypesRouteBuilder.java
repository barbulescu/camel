package com.barbulescu.camel;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BeanTypesRouteBuilder extends EndpointRouteBuilder {


    @Override
    public void configure() {
        from(direct("bean"))
                .routeId("bean")
                .bean(new SimpleBean("some value"));
    }
}
