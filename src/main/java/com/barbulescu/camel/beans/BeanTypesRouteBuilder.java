package com.barbulescu.camel.beans;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("beans")
public class BeanTypesRouteBuilder extends EndpointRouteBuilder {

    private final SimpleBean bean;

    public BeanTypesRouteBuilder(SimpleBean bean) {
        this.bean = bean;
    }

    @Override
    public void configure() {
        from(direct("beanIn"))
                .routeId("beanRoute")
                .bean(bean)
                .to(direct("beanOut")
                        .block(false)
                        .failIfNoConsumers(false));
    }

    @Configuration
    static class BeanConfig {

        @Bean
        SimpleBean simpleBean() {
            return new SimpleBean("some value");
        }
    }
}
