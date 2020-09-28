package com.barbulescu.camel.jms.client;

import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

public class MyBodyAppender {

    public static final String END = "END";

    public List<String> append(
            List<String> existing,
            Map<String, String> existingHeaders,
            Map<String, String> existingProperties,
            List<String> next,
            Map<String, String> nextHeaders,
            Map<String, String> nextProperties) {

        List<String> aggregate = ofNullable(existing)
                .map(s -> concat(s.stream(), next.stream())
                        .filter(x -> !END.equals(x))
                        .collect(toList()))
                .orElse(next);

        next.stream()
                .filter(s -> END.equals(s))
                .forEach(s -> nextProperties.put("aggregationDone", "true"));
        return aggregate;
    }
}
