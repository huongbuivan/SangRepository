package com.personal.javastudy.week2.memory_type.execise1;

/**
 * please add those flags before start app: -Xss136k
 */
public class StackOverflowDemo {
    private static int count = 0;

    public static int factorial(int n) {
        count++;
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        long heapSize = Runtime.getRuntime().totalMemory();
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        long freeHeapSize = Runtime.getRuntime().freeMemory();

        System.out.println("Heap size: " + heapSize / (1024 * 1024) + " MB");
        System.out.println("Max heap size: " + maxHeapSize / (1024 * 1024) + " MB");
        System.out.println("Free heap size: " + freeHeapSize / (1024 * 1024) + " MB");

        try {
            System.out.println(factorial(1000)); // Exaggerated input to cause stack overflow
        } catch (StackOverflowError e) {
            System.out.println("Stack Overflow occurred: " + e);
            System.out.println("Maximum stack depth: " + count);
        }
    }
}
