package com.barbulescu.camel.jms.client;

import org.apache.camel.Body;
import org.apache.camel.Handler;

public class TrimBean {

    @Handler
    String trim(@Body String body) {
        return body.trim();
    }
}
