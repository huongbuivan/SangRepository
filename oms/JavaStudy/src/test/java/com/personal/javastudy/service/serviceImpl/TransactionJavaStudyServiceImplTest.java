package com.personal.javastudy.service.serviceImpl;

import com.personal.javastudy.dtos.transactions.TaskRequest;
import com.personal.javastudy.models.transactions.Account;
import com.personal.javastudy.models.transactions.Task;
import com.personal.javastudy.repositories.AccountRepository;
import com.personal.javastudy.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionJavaStudyServiceImplTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JavaStudyServiceImpl javaStudyServiceImpl;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll(); // Clear the database before each test
        taskRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    void testGetAllAccounts_ShouldReturnAllAccounts() {
        Account account1 = new Account(null, "ACC001", 10000.0);
        Account account2 = new Account(null, "ACC002", 20000.0);
        accountRepository.save(account1);
        accountRepository.save(account2);

        List<Account> accounts = javaStudyServiceImpl.getAllAccounts();

        assertEquals(2, accounts.size());
        assertTrue(accounts.stream().anyMatch(account -> (account.getAccountNumber().equals("ACC001") && account.getBalance() == 10000)));
        assertTrue(accounts.stream().anyMatch(a -> (a.getAccountNumber().equals("ACC002") && a.getBalance() == 20000)));
    }

    @Test
    void testCreateAccount_ShouldCreateAccountSuccessfully() {
        Account account = javaStudyServiceImpl.createAccount("ACC003", 1500.0);

        assertNotNull(account.getId());
        assertEquals("ACC003", account.getAccountNumber());
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    void testCreateAccount_ShouldThrowExceptionForNegativeBalance() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            javaStudyServiceImpl.createAccount("ACC004", -500.0);
        });

        assertEquals("Initial balance cannot be negative.", exception.getMessage());
    }

    @Test
    void testCreateAccount_ShouldThrowExceptionForDuplicateAccount() {
        // Arrange
        javaStudyServiceImpl.createAccount("ACC005", 500.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            javaStudyServiceImpl.createAccount("ACC005", 1000.0);
        });

        assertEquals("Account with number ACC005 already exists.", exception.getMessage());
    }

    @Test
    void testTransferMoney_ShouldTransferSuccessfully() {
        Account sender = javaStudyServiceImpl.createAccount("ACC006", 3000.0);
        Account receiver = javaStudyServiceImpl.createAccount("ACC007", 1000.0);

        javaStudyServiceImpl.transferMoney(sender.getAccountNumber(), receiver.getAccountNumber(), 500.0);

        Account updatedSender = accountRepository.findByAccountNumber(sender.getAccountNumber()).orElseThrow();
        Account updatedReceiver = accountRepository.findByAccountNumber(receiver.getAccountNumber()).orElseThrow();

        assertEquals(2500.0, updatedSender.getBalance());
        assertEquals(1500.0, updatedReceiver.getBalance());
    }

    @Test
    void testTransferMoney_ShouldRollbackOnError() {
        Account sender = javaStudyServiceImpl.createAccount("ACC008", 6000.0);
        Account receiver = javaStudyServiceImpl.createAccount("ACC009", 2000.0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            javaStudyServiceImpl.transferMoney(sender.getAccountNumber(), receiver.getAccountNumber(), 5000.0);
        });

        assertEquals("Simulated error occurred. Rolling back...", exception.getMessage());

        Account updatedSender = accountRepository.findByAccountNumber(sender.getAccountNumber()).orElseThrow();
        Account updatedReceiver = accountRepository.findByAccountNumber(receiver.getAccountNumber()).orElseThrow();

        assertEquals(6000.0, updatedSender.getBalance()); // No deduction
        assertEquals(2000.0, updatedReceiver.getBalance()); // No addition
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task(1L, "Task 1", "Description 1", "CREATED");
        Task task2 = new Task(2L, "Task 2", "Description 2", "CREATED");
        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = javaStudyServiceImpl.getAllTasks();

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertTrue(tasks.stream()
                .anyMatch(task -> task.getName().equals("Task 1")
                        && task.getDescription().equals("Description 1")
                        && task.getStatus().equals("CREATED")));
        assertTrue(tasks.stream()
                .anyMatch(task -> task.getName().equals("Task 2")
                                && task.getDescription().equals("Description 2")
                                && task.getStatus().equals("CREATED")));
    }

    @Test
    void testCreateTask_Success() {
        TaskRequest request = new TaskRequest("Task 3", "Description 3");

        javaStudyServiceImpl.createTask(request);

        List<Task> tasks = javaStudyServiceImpl.getAllTasks();

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertTrue(tasks.stream()
                .anyMatch(task -> task.getName().equals("Task 3")
                        && task.getDescription().equals("Description 3")
                        && task.getStatus().equals("CREATED")));
        assertTrue(tasks.stream()
                .anyMatch(task -> task.getName().equals("Audit Log")
                        && task.getStatus().equals("LOGGED")));
    }

    @Test
    void testCreateTask_RollbackOnException() {
        TaskRequest request = new TaskRequest("fail", "Description 1");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> javaStudyServiceImpl.createTask(request));
        assertEquals("Simulated exception during task creation!", exception.getMessage());

        List<Task> tasks = javaStudyServiceImpl.getAllTasks();
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertTrue(tasks.stream()
                .anyMatch(task -> task.getName().equals("Audit Log")
                        && task.getStatus().equals("LOGGED")));
    }
}
