package com.personal.javastudy.week1.garbage_collection_and_memory_leaks.exercise1;

/**
 * The MemoryUsageDemo class demonstrates memory allocation and garbage collection in Java.
 * <p>
 * This program continuously allocates 1 MB chunks of memory and then releases the
 * reference to the allocated memory, allowing the Garbage Collector (GC) to reclaim it.
 * The process is slowed down using a small delay to make memory usage patterns easier
 * to observe.
 */
public class MemoryUsageDemo {
    public static void main(String[] args) {
        while (true) {
            // Allocate 1 MB of memory
            byte[]  data = new byte[1024 * 1024];
            System.out.println("Allocated 1 MB");

            try {
                // Slow down the memory allocation rate
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
