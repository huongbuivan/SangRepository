package com.personal.javastudy.week2.data_type.execise1;

public class ImmutabilityOfIntegerDemo {
    public static void main(String[] args) {
        Integer original = 10;
        Integer modified = original + 5;

        System.out.println("Original Integer: " + original);  // Output: 10
        System.out.println("Modified Integer: " + modified);  // Output: 15
    }
}
