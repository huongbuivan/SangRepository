package com.personal.javastudy.service;

import com.personal.javastudy.dtos.function_interface.OperationRequest;
import com.personal.javastudy.dtos.function_interface.OperationResponse;
import com.personal.javastudy.dtos.function_interface.StringFilterRequest;
import com.personal.javastudy.dtos.memory_types.ImmutablePerson;

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
}
