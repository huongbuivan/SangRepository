package com.personal.javastudy.service.serviceImpl;

@FunctionalInterface
public interface StringCondition {
    boolean check(String str, String filterValue);
}
