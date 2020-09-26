package com.barbulescu.camel.folder;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("folder")
public class FolderCopyRouteBuilder extends EndpointRouteBuilder {
    static final String SOURCE = "/tmp/camel_source";
    static final String TARGET = "/tmp/camel_destination";

    @Override
    public void configure() {
        from(file(SOURCE))
                .routeId("folderCopy")
                .to(file(TARGET));
    }
}
