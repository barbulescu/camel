package com.barbulescu.camel;

import com.barbulescu.camel.model.Type1;
import com.barbulescu.camel.model.Type2;
import org.apache.camel.Body;
import org.apache.camel.Handler;

public class SimpleBean {

    @Handler
    public Type2 convertTypes(@Body Type1 type1) {
        return Type2.of(type1.fullName().length());
    }
}
