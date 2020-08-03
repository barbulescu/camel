package com.barbulescu.camel;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FolderCopyRouteBuilder extends EndpointRouteBuilder {


    @Override
    public void configure() {
        from(file("/tmp/camel_source").noop(true))
                .routeId("folderCopy")
                .to(file("/tmp/camel_destination"));
    }
}
