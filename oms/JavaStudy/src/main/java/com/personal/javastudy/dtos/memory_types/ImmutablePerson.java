package com.personal.javastudy.dtos.memory_types;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class ImmutablePerson {
    private final String name;
    private final int age;

    public ImmutablePerson(final String name, final int age) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        this.age = age;
        this.name = name;
    }
}
