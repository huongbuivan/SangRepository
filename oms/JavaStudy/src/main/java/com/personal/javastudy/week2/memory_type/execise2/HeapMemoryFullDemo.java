package com.personal.javastudy.week2.memory_type.execise2;

import java.util.ArrayList;
import java.util.List;

/**
 * please add those flags before start app: -Xms256m -Xmx1024m
 */
public class HeapMemoryFullDemo {
    public static void createObjects(int numObjects) {
        List<byte[]> list = new ArrayList<>();
        for (int i = 1; i <= numObjects; i++) {
            // Allocating about 1MB per object
            list.add(new byte[1024 * 1023]);
            System.out.println("Created object " + i + " of size about 1MB");
        }
    }

    public static void main(String[] args) {
        long heapSize = Runtime.getRuntime().totalMemory();
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        long freeHeapSize = Runtime.getRuntime().freeMemory();

        System.out.println("Heap size: " + heapSize / (1024 * 1024) + " MB");
        System.out.println("Max heap size: " + maxHeapSize / (1024 * 1024) + " MB");
        System.out.println("Free heap size: " + freeHeapSize / (1024 * 1024) + " MB");

        try {
            createObjects(1030);
            System.out.println("Finished object creation. Monitor memory usage.");
        } catch (OutOfMemoryError e) {
            System.out.println("Heap Memory Full: " + e);
        }
    }
}
