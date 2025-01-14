package com.personal.javastudy.controllers;

import com.personal.javastudy.dtos.transactions.TaskRequest;
import com.personal.javastudy.models.transactions.Account;
import com.personal.javastudy.models.transactions.Task;
import com.personal.javastudy.service.JavaStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction/")
@RequiredArgsConstructor
public class TransactionController {

    private final JavaStudyService javaStudyservice;

    /**
     * Scenario
     * This will involve related database operations that must either all succeed or all fail, demonstrating the rollback behavior of @Transactional.
     * <p>
     * Scenario: Bank Transfer
     * We have an endpoint api/transaction/transfer that handles transferring money between two accounts. The operations are:
     * <p>
     * 1. Deduct the amount from the sender's account.
     * <p>
     * 2. Add the amount to the receiver's account.
     * <p>
     * 3. Rollback all operations if an error occurs (simulation send "5000S" -> an error occurs).
     */

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = javaStudyservice.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(
            @RequestParam String accountNumber,
            @RequestParam Double initialBalance) {
        try {
            Account account = javaStudyservice.createAccount(accountNumber, initialBalance);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Account creation failed: " + e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(
            @RequestParam String senderAccountNumber,
            @RequestParam String receiverAccountNumber,
            @RequestParam Double amount) {
        try {
            javaStudyservice.transferMoney(senderAccountNumber, receiverAccountNumber, amount);
            return ResponseEntity.ok("Transfer successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Transfer failed: " + e.getMessage());
        }
    }

    /**
     * Scenario
     * <p>
     * We have a service managing a Task table. When creating a new task:
     * <p>
     * 1. The task creation is done in the main transaction (REQUIRED). (simulation if the name of main task is "fail" -> an error occurs).
     * <p>
     * 2. A log entry is created in the same table for audit purposes using a separate transaction (REQUIRES_NEW)
     * to ensure it succeeds even if the main transaction fails.
     */

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = javaStudyservice.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create-tasks")
    public ResponseEntity<String> createTask(@RequestBody TaskRequest request) {
        try {
            javaStudyservice.createTask(request);
            return ResponseEntity.ok("Task created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create task: " + e.getMessage());
        }
    }
}
