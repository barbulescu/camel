package com.barbulescu.camel.folder;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("folder")
public class FolderCopyRouteBuilder extends EndpointRouteBuilder {


    @Override
    public void configure() {
        from(file("/tmp/camel_source").noop(true))
                .routeId("folderCopy")
                .to(file("/tmp/camel_destination"));
    }
}
