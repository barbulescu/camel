package com.barbulescu.camel.beans;

import com.barbulescu.camel.beans.model.Type1;
import com.barbulescu.camel.beans.model.Type2;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.apache.camel.component.mock.MockEndpoint.assertIsSatisfied;
import static org.assertj.core.api.Assertions.assertThat;

@CamelSpringBootTest
@SpringBootTest(classes = {CamelAutoConfiguration.class, ProcessorTypesRouteBuilder.class})
@ActiveProfiles("beans")
@MockEndpoints
class ProcessorTypesRouteBuilderTest {

    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject("mock:direct:processorOut")
    private MockEndpoint beanOut;

    @Test
    void basicProcessorRoute() throws InterruptedException {
        assertThat(producerTemplate).isNotNull();
        assertThat(beanOut).isNotNull();


        beanOut.expectedMessageCount(1);
        beanOut.expectedBodiesReceived(Type2.of(3));
        beanOut.expectedPropertyReceived("key1", "some value");

        producerTemplate.sendBody("direct:processorIn", Type1.of("abc"));

        assertIsSatisfied(1, TimeUnit.SECONDS);
    }

}