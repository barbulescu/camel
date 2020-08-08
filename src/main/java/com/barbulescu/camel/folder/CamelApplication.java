package com.barbulescu.camel.folder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CamelApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CamelApplication.class)
				.run(args);
	}

}
