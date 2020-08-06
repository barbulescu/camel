package com.barbulescu.camel;

import com.barbulescu.camel.model.Type1;
import com.barbulescu.camel.model.Type2;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Handler;

import java.util.Map;

public class SimpleBean {

    private final String specialValue;

    public SimpleBean(String specialValue) {
        this.specialValue = specialValue;
    }

    @Handler
    public Type2 convertTypes(@Body Type1 type1, @ExchangeProperties Map<String, String> properties) {
        properties.putIfAbsent("key1", specialValue);
        return Type2.of(type1.fullName().length());
    }
}
