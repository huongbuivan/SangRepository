package com.personal.javastudy.service.serviceImpl.function_interface;

@FunctionalInterface
public interface StringCondition {
    boolean check(String str, String filterValue);
}
