package com.personal.javastudy.week2.data_type.execise2;

public class ImmutablePersonDemo {
    public static void main(String[] args) {
        ImmutablePerson person = new ImmutablePerson("Alice", 30);

        System.out.println("Name: " + person.getName());  // Output: Alice
        System.out.println("Age: " + person.getAge());    // Output: 30

        // each of the attributes of ImmutablePerson class has been declared final.
        // This will prevent the properties from being changed.
        // Hence, this class is very safe to use in concurrent context (multithreaded environment)
        // as there is no possibility of conflicts while accessing data.
        // person.name = "Bob";   // error
    }
}
