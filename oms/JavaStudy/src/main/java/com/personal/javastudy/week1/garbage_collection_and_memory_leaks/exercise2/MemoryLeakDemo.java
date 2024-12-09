package com.personal.javastudy.week1.garbage_collection_and_memory_leaks.exercise2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The MemoryLeakDemo class simulates a memory leak scenario by continuously adding
 * new entries to a static cache (HashMap) without any eviction or cleanup mechanism.
 * This results in increasing memory usage over time and can eventually lead to
 * performance degradation or an OutOfMemoryError.
 * <p>
 * The cache is static, meaning it will persist for the entire lifetime of the application,
 * making it difficult for the garbage collector (GC) to reclaim memory, which simulates
 * the effects of a memory leak.
 */
public class MemoryLeakDemo {
    // A static Map to simulate a memory leak by storing key-value pairs indefinitely.
    private static Map<String, String> cache = new HashMap<>();

    public static void main(String[] args) {
        // Initialize a counter variable to keep track of iterations.
        int i = 1;
        // Infinite loop to continuously add entries to the cache, simulating memory usage growth.
        while (true) {
            // Simulate a memory leak by adding new entries to the cache with unique keys and values.
            // The cache keeps growing as new entries are added, leading to potential memory exhaustion.
            cache.put("Key-" + UUID.randomUUID(), "Value-" + UUID.randomUUID());

            // Periodically print the size of the cache for monitoring purposes.
            // Every 1000 iterations, display the current size of the cache.
            if (i % 1000 == 0) {
                System.out.println("Cache size: " + cache.size());
            }

            // Increment the counter to continue adding new entries on each loop iteration.
            i++;
        }
    }
}
