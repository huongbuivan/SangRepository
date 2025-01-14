package com.personal.javastudy.service;

import com.personal.javastudy.dtos.function_interface.OperationRequest;
import com.personal.javastudy.dtos.function_interface.OperationResponse;
import com.personal.javastudy.dtos.function_interface.StringFilterRequest;
import com.personal.javastudy.dtos.memory_types.ImmutablePerson;
import com.personal.javastudy.dtos.transactions.TaskRequest;
import com.personal.javastudy.models.transactions.Account;
import com.personal.javastudy.models.transactions.Task;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface JavaStudyService {
    OperationResponse performOperation(OperationRequest request);

    List<String> filterStrings(StringFilterRequest request);

    void consumeHeap(int size);

    void createMemoryLeak(int iterations);

    void clearMemoryLeak();

    long recursiveMethod(int n);

    String demonstrateStringImmutability(String original, String appendText);

    String demonstrateIntegerImmutability(Integer original, int increment);

    String demonstrateLocalDateImmutability(LocalDate originalDate, int daysToAdd);

    ImmutablePerson createImmutablePerson(String name, int age);

    CompletableFuture<String> fetchCombinedData();

    int sumUsingForkJoin(int[] array);

    int sumUsingTraditionalThreads(int[] array) throws InterruptedException;

    List<String> scrapeWebsites(String[] urls) throws InterruptedException, ExecutionException;

    String hashPassword(String password);

    String generateSecretKey();

    String encrypt(String plainText, String secretKey);

    String decrypt(String encryptedText, String secretKey);

    List<Account> getAllAccounts();

    @Transactional
    Account createAccount(String accountNumber, Double initialBalance);

    @Transactional
    void transferMoney(String senderAccountNumber, String receiverAccountNumber, Double amount);

    List<Task> getAllTasks();

    @Transactional(propagation = Propagation.REQUIRED)
    void createTask(TaskRequest request);
}
