package com.barbulescu.camel.model;

import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of")
@Getter
public class Type1 {
    public String fullName;
}
