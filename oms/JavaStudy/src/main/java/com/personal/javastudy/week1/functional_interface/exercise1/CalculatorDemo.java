package com.personal.javastudy.week1.functional_interface.exercise1;

public class CalculatorDemo {

    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println("Addition: " + add.operate(5, 5));
        System.out.println("Subtraction: " + subtract.operate(5, 5));
        System.out.println("Multiplication: " + multiply.operate(5, 5));
    }
}
