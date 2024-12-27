package com.personal.javastudy.controllers;

import com.personal.javastudy.service.JavaStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/garbage-collection/")
@RequiredArgsConstructor
public class GarbageCollectionController {

    private final JavaStudyService javaStudyservice;

    @PostMapping("/consume-heap")
    public ResponseEntity<String> memoryUsage(
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            javaStudyservice.consumeHeap(size);
            return ResponseEntity.ok("heap consumption completed successfully!");
        } catch (OutOfMemoryError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Heap memory exceeded!");
        }
    }

    @PostMapping("/create-memory-Leak")
    public ResponseEntity<String> createMemoryLeak(
            @RequestParam(value = "iterations", defaultValue = "1000") int iterations) {
        javaStudyservice.createMemoryLeak(iterations);
        return ResponseEntity.ok("Memory leak simulation started!");
    }

    @PostMapping("/clear-memory-leak")
    public ResponseEntity<String> clearMemoryLeak() {
        javaStudyservice.clearMemoryLeak();
        return ResponseEntity.ok("Memory leak cleared!");
    }
}
