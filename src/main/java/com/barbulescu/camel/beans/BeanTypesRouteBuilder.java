package com.barbulescu.camel.beans;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("beans")
public class BeanTypesRouteBuilder extends EndpointRouteBuilder {

    @Override
    public void configure() {
        from(direct("bean"))
                .routeId("bean")
                .bean(new SimpleBean("some value"));
    }
}
