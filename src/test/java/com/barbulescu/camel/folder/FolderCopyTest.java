package com.barbulescu.camel.folder;

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

import static com.barbulescu.camel.folder.FolderCopyRouteBuilder.SOURCE;
import static com.barbulescu.camel.folder.FolderCopyRouteBuilder.TARGET;
import static org.assertj.core.api.Assertions.assertThat;

@CamelSpringBootTest
@SpringBootTest(classes = {CamelAutoConfiguration.class, FolderCopyRouteBuilder.class})
@ActiveProfiles("folder")
@MockEndpoints
class FolderCopyTest {

    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject("mock:file:" + TARGET)
    private MockEndpoint mockCamel;


    @Test
    public void testReceive() throws Exception {
        assertThat(producerTemplate).isNotNull();
        assertThat(mockCamel).isNotNull();

        mockCamel.expectedMessageCount(1);
        mockCamel.expectedBodiesReceived("a1");

        producerTemplate.sendBody("file:" + SOURCE, "a1");

        mockCamel.assertIsSatisfied();
    }
}
