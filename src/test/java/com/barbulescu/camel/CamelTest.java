package com.barbulescu.camel;

import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.DisableJmx;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.annotation.DirtiesContext;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@Target(ElementType.TYPE)
@Documented
@CamelSpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@EnableAutoConfiguration
@DisableJmx
public @interface CamelTest {
}
