package com.barbulescu.camel;

import com.barbulescu.camel.model.Type1;
import com.barbulescu.camel.model.Type2;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Route;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.CamelSpringTestSupport;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@CamelSpringBootTest
@ContextConfiguration
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class TypesRouteBuilderTest extends CamelSpringTestSupport {

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

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(FolderCopyRouteBuilder.class.getPackageName());
    }
}