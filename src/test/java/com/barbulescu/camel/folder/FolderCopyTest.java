package com.barbulescu.camel.folder;

import com.barbulescu.camel.CamelTest;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.camel.component.mock.MockEndpoint.assertIsSatisfied;
import static org.assertj.core.api.Assertions.assertThat;

@CamelTest
@SpringBootTest(classes = {CamelAutoConfiguration.class, FolderCopyRouteBuilder.class})
@ActiveProfiles("folder")
@MockEndpoints
class FolderCopyTest {

    @EndpointInject("mock:/tmp/camel_source")
    private MockEndpoint source;
    @EndpointInject("mock:/tmp/camel_destination")
    private MockEndpoint destination;

    @Autowired
    private ProducerTemplate template;

    @Autowired
    private SpringCamelContext context;

    @Test
    public void testReceive() throws Exception {
        template.setDefaultEndpoint(source);

        assertThat(template).isNotNull();
        source.expectedBodiesReceived("Hallo");
        destination.expectedBodiesReceived("Hallo");

        template.sendBody("Hallo");

        assertIsSatisfied(5, SECONDS);
    }
}
