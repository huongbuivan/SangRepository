package com.personal.javastudy.week1.functional_interface.exersice2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringFilterDemo {
    public static void main(String[] args) {
        List<String> nameLists = Arrays.asList("James", "Emma", "William", "Olivia", "Ethan");

        // Filter nameLists by length > 5
        List<String> longStrings = filterStrings(nameLists, s -> s.length() > 5);
        System.out.println("nameLists with length > 5: " + longStrings);

        // Filter nameLists that start with 'a'
        List<String> startsWithO = filterStrings(nameLists, s -> s.startsWith("O"));
        System.out.println("nameLists starting with 'o': " + startsWithO);

        // Filter nameLists that contain 'e'
        List<String> containsE = filterStrings(nameLists, s -> s.contains("e"));
        System.out.println("nameLists containing 'e': " + containsE);
    }

    public static List<String> filterStrings(List<String> strings, StringCondition condition) {
        List<String> results = new ArrayList<>();
        for (String string : strings) {
            if (condition.check(string)) {
                results.add(string);
            }
        }
        return results;
    }

}
