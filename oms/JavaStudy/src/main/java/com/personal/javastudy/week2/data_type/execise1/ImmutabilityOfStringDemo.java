package com.personal.javastudy.week2.data_type.execise1;

public class ImmutabilityOfStringDemo {
    public static void main(String[] args) {
        String original = "Hello";
        String modified = original.concat(" World");

        System.out.println("Original String: " + original);  // Output: Hello
        System.out.println("Modified String: " + modified);  // Output: Hello World
    }
}
