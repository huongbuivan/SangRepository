package com.personal.javastudy.week2.data_type.execise2;

import lombok.Getter;

@Getter
public class ImmutablePerson {
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
