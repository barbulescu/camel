package com.barbulescu.camel;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.CamelSpringTestSupport;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@CamelSpringBootTest
@ContextConfiguration
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@MockEndpoints
class CamelApplicationTests extends CamelSpringTestSupport {

    @Override
    public String isMockEndpoints() {
        return "*";
    }

    @Test
    public void testReceive() throws Exception {
        MockEndpoint source = findMock("source");
        MockEndpoint destination = findMock("destination");
        template.setDefaultEndpoint(source);

        assertThat(template).isNotNull();
        source.expectedBodiesReceived("Hallo");
        destination.expectedBodiesReceived("Hallo");

        template.sendBody("Hallo");

        MockEndpoint.assertIsSatisfied(5, SECONDS);
        MockEndpoint.assertIsSatisfied(5, SECONDS);
    }

    private MockEndpoint findMock(String suffix) {
        return (MockEndpoint) context.getEndpoints().stream()
                .filter(it -> it.getEndpointUri().endsWith(suffix))
                .findAny()
                .orElseThrow();
    }

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new FolderCopyRouteBuilder();
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(FolderCopyRouteBuilder.class.getPackageName());
    }
}
