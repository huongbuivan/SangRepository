package com.personal.javastudy.controllers;

import com.personal.javastudy.dtos.cryptography.PasswordRequest;
import com.personal.javastudy.service.JavaStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cryptography/")
@RequiredArgsConstructor
public class CryptographyController {

    private final JavaStudyService javaStudyservice;

    @PostMapping("password/hash")
    public ResponseEntity<String> hashPassword(@RequestBody PasswordRequest request) {
        return ResponseEntity.ok(javaStudyservice.hashPassword(request.getPassword()));
    }

    @GetMapping("/generate-key")
    public ResponseEntity<String> generateSecretKey() {
        String secretKey = javaStudyservice.generateSecretKey();
        return new ResponseEntity<>(secretKey, HttpStatus.CREATED);
    }

    @PostMapping("/encrypt")
    public ResponseEntity<String> encryptText(@RequestParam String plainText, @RequestParam String secretKey) {
        try {
            String encryptedText = javaStudyservice.encrypt(plainText, secretKey);
            return new ResponseEntity<>(encryptedText, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> decryptText(@RequestParam String encryptedText, @RequestParam String secretKey) {
        try {
            String decryptedText = javaStudyservice.decrypt(encryptedText, secretKey);
            return new ResponseEntity<>(decryptedText, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
