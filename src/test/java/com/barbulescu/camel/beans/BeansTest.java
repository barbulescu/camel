package com.barbulescu.camel.beans;

import com.barbulescu.camel.CamelTest;
import com.barbulescu.camel.beans.model.Type1;
import com.barbulescu.camel.beans.model.Type2;
import org.apache.camel.*;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@CamelTest
@SpringBootTest(classes = {CamelAutoConfiguration.class, BeanTypesRouteBuilder.class, ProcessorTypesRouteBuilder.class})
@ActiveProfiles("beans")
class BeansTest {

    @Autowired
    private CamelContext context;

    @Autowired
    private FluentProducerTemplate fluentTemplate;

    private static Stream<Arguments> typesRouteData() {
        return Stream.of(
                Arguments.of("bean"),
                Arguments.of("processor")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("typesRouteData")
    void typesRouteBody(String routeId) {
        Route route = context.getRoute(routeId);
        fluentTemplate.setDefaultEndpoint(route.getEndpoint());
        Exchange abc = fluentTemplate.withBody(Type1.of("abc")).send();
        assertThat(abc)
                .extracting(Exchange::getIn)
                .extracting(Message::getBody)
                .isEqualTo(Type2.of(3));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("typesRouteData")
    void typesRouteProperties(String routeId) {
        Route route = context.getRoute(routeId);
        fluentTemplate.setDefaultEndpoint(route.getEndpoint());
        Exchange abc = fluentTemplate.withBody(Type1.of("abc")).send();
        assertThat(abc)
                .extracting(it -> it.getProperty("key1"))
                .isEqualTo("some value");
    }
}