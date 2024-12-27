package com.personal.javastudy.controllers;

import com.personal.javastudy.dtos.memory_types.ImmutablePerson;
import com.personal.javastudy.service.JavaStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/data-types/")
@RequiredArgsConstructor
public class DataTypesController {

    private final JavaStudyService javaStudyservice;

    @GetMapping("/demonstrate-string-mutability")
    public ResponseEntity<String> demonstrateStringImmutability(
            @RequestParam String original,
            @RequestParam String appendText) {
        return ResponseEntity.ok(javaStudyservice.demonstrateStringImmutability(original, appendText));
    }

    @GetMapping("/demonstrate-integer-mutability")
    public ResponseEntity<String> demonstrateIntegerImmutability(
            @RequestParam Integer original,
            @RequestParam int increment) {
        return ResponseEntity.ok(javaStudyservice.demonstrateIntegerImmutability(original, increment));
    }

    @GetMapping("/demonstrate-localDate-mutability")
    public ResponseEntity<String> demonstrateLocalDateImmutability(
            @RequestParam String original, @RequestParam int daysToAdd) {
        LocalDate originalDate = LocalDate.parse(original);
        return ResponseEntity.ok(javaStudyservice.demonstrateLocalDateImmutability(originalDate, daysToAdd));
    }

    @PostMapping("/create-immutable-person")
    public ResponseEntity<ImmutablePerson> createImmutablePerson(
            @RequestParam String name,
            @RequestParam int age) {
        return ResponseEntity.ok(javaStudyservice.createImmutablePerson(name, age));
    }
}
