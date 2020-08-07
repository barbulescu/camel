package com.barbulescu.camel.beans;

import com.barbulescu.camel.beans.model.Type1;
import com.barbulescu.camel.beans.model.Type2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SimpleProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        Type1 type1 = (Type1) exchange.getIn().getBody();
        Type2 type2 = Type2.of(type1.fullName().length());
        exchange.getIn().setBody(type2);

        exchange.setProperty("key1", "some value");
    }
}
