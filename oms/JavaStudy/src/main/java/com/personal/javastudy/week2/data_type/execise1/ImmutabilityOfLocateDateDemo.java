package com.personal.javastudy.week2.data_type.execise1;

import java.time.LocalDate;

public class ImmutabilityOfLocateDateDemo {
    public static void main(String[] args) {
        LocalDate originalDate = LocalDate.of(2024, 12, 18);
        LocalDate modifiedDate = originalDate.plusDays(5);

        System.out.println("Original Date: " + originalDate);  // Output: 2024-12-18
        System.out.println("Modified Date: " + modifiedDate);  // Output: 2024-12-23
    }
}
