package com.personal.javastudy.config;

import com.personal.javastudy.service.serviceImpl.Calculator;
import com.personal.javastudy.service.serviceImpl.StringCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean
    public Map<String, Calculator> operations() {
        Map<String, Calculator> operations = new HashMap<>();

        operations.put("add", (a, b) -> a + b);
        operations.put("subtract", (a, b) -> a - b);
        operations.put("multiply", (a, b) -> a * b);
        operations.put("divide", (a, b) -> {
            if (b == 0) throw new IllegalArgumentException("Division by zero is not allowed");
            return a / b;
        });

        return operations;
    }

    @Bean
    public Map<String, StringCondition> filterStrings() {
        Map<String, StringCondition> filterStrings = new HashMap<>();

        filterStrings.put("startWith", (str, filterValue) -> str.startsWith(filterValue));
        filterStrings.put("contains", (str, filterValue) -> str.contains(filterValue));

        return filterStrings;
    }

    @Bean
    public List<byte[]> memoryConsumers() {
        return new ArrayList<>();
    }

    @Bean
    public Map<Integer, String> leakMap() {
        return new HashMap<>();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(4);
    }
}
