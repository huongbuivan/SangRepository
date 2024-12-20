package com.personal.javastudy.controllers;

import com.personal.javastudy.service.JavaStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/memory-types/")
@RequiredArgsConstructor
public class MemoryTypesController {

    private final JavaStudyService javaStudyservice;

    @GetMapping("/factorial/{n}")
    public ResponseEntity<String> calculateFactorial(
            @PathVariable int n) {
        try {
            long result = javaStudyservice.recursiveMethod(n);
            return ResponseEntity.ok("Factorial of " + n + " is " + result);
        } catch (StackOverflowError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Stack overflow occurred while calculating factorial for n = " + n);
        }
    }
}
