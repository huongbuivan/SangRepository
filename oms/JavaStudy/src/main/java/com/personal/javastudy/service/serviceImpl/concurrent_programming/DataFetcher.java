package com.personal.javastudy.service.serviceImpl.concurrent_programming;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class DataFetcher {
    public CompletableFuture<String> fetchDataFromSource1() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data from Source 1";
        });
    }

    public CompletableFuture<String> fetchDataFromSource2() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1500); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data from Source 2";
        });
    }
}
