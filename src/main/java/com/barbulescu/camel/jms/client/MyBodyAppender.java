package com.barbulescu.camel.jms.client;

import java.util.Map;

import static java.util.Optional.ofNullable;

public class MyBodyAppender {

    public String append(
            String existing,
            Map<String, String> existingHeaders,
            Map<String, String> existingProperties,
            String next,
            Map<String, String> nextHeaders,
            Map<String, String> nextProperties) {

        String aggregate = ofNullable(existing)
                .map(s -> s + next)
                .orElse(next);

        if (next.endsWith(">")) {
            nextProperties.put("aggregationDone", "true");
        }
        return aggregate;
    }
}
