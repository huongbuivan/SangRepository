package com.personal.javastudy.controllers;

import com.personal.javastudy.service.JavaStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/concurrent-programming/")
@RequiredArgsConstructor
public class ConcurrentProgrammingController {

    private final JavaStudyService javaStudyservice;

    @GetMapping("/fetch-data")
    public ResponseEntity<String> fetchData() {
        CompletableFuture<String> combinedData = javaStudyservice.fetchCombinedData();
        try {
            String result = combinedData.get();
            return ResponseEntity.ok(result);
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(500).body("Error occurred while fetching data");
        }
    }

    @GetMapping("/sum/fork-join")
    public ResponseEntity<String> sumUsingForkJoin(@RequestParam int[] array) {
        long startTime = System.currentTimeMillis();
        int result = javaStudyservice.sumUsingForkJoin(array);
        long endTime = System.currentTimeMillis();
        return ResponseEntity.ok("Result: " + result + ", Time taken: " + (endTime - startTime) + "ms");
    }

    @GetMapping("/sum/traditional-thread")
    public ResponseEntity<String> sumUsingTraditionalThreads(@RequestParam int[] array) {
        try {
            long startTime = System.currentTimeMillis();
            int result = javaStudyservice.sumUsingTraditionalThreads(array);
            long endTime = System.currentTimeMillis();
            return ResponseEntity.ok("Result: " + result + ", Time taken: " + (endTime - startTime) + "ms");
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body("Error occurred during traditional thread processing");
        }
    }

    @PostMapping("/scrape-websites")
    public List<String> scrapeWebsites(@RequestBody String[] urls) throws InterruptedException, ExecutionException {
        return javaStudyservice.scrapeWebsites(urls);
    }
}
