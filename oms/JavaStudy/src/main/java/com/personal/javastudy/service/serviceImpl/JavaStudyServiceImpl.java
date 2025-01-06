package com.personal.javastudy.service.serviceImpl;

import com.personal.javastudy.dtos.function_interface.OperationRequest;
import com.personal.javastudy.dtos.function_interface.OperationResponse;
import com.personal.javastudy.dtos.function_interface.StringFilterRequest;
import com.personal.javastudy.dtos.memory_types.ImmutablePerson;
import com.personal.javastudy.service.JavaStudyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class JavaStudyServiceImpl implements JavaStudyService {

    private final Map<String, Calculator> operations;
    private final Map<String, StringCondition> filterStrings;
    private final List<byte[]> memoryConsumers;
    private final Map<Integer, String> leakMap;
    private final DataFetcher dataFetcher;
    private final ExecutorService executorService;

    private final String hashingAlgorithm;
    private final int saltLength;
    private final int hashIterations;

    private final String encryptionAlgorithm;
    private final int keySize;


    @Override
    public OperationResponse performOperation(@NonNull OperationRequest request) {
        Calculator calculator = operations.get(request.getOperation());
        if (calculator == null) {
            throw new IllegalArgumentException("Unsupported operation: " + request.getOperation());
        }
        double result = calculator.operate(request.getNumber1(), request.getNumber2());
        return OperationResponse.builder().result(result).build();
    }

    @Override
    public List<String> filterStrings(@NonNull StringFilterRequest request) {
        StringCondition stringFilter = filterStrings.get(request.getFilterType());
        if (stringFilter == null) {
            throw new IllegalArgumentException("Unsupported filterStrings: " + request.getFilterType());
        }
        List<String> results = new ArrayList<>();
        for (String string : request.getStrings()) {
            if (stringFilter.check(string, request.getFilterValue())) {
                results.add(string);
            }
        }
        return results;
    }

    @Override
    public void consumeHeap(int sizeInMB) {
        if (sizeInMB == -1) { // Clear memoryConsumers
            memoryConsumers.clear();
        } else if (sizeInMB == -2) { // Trigger GC
            System.gc();
        } else {
            int sizeInBytes = sizeInMB * 1024 * 1024;
            memoryConsumers.add(new byte[sizeInBytes]);
        }
    }

    @Override
    public void createMemoryLeak(int iterations) {
        for (int i = 0; i < iterations; i++) {
            leakMap.put(i, "leaking data " + System.nanoTime());
        }
    }

    @Override
    public void clearMemoryLeak() {
        leakMap.clear();
    }

    @Override
    public long recursiveMethod(int n) {
        if (n == 0) {
            return 1;
        }
        return n * recursiveMethod(n - 1);
    }

    @Override
    public String demonstrateStringImmutability(String original, String appendText) {
        String modified = original + appendText;
        return "Original: " + original + ", Modified: " + modified;
    }

    @Override
    public String demonstrateIntegerImmutability(Integer original, int increment) {
        Integer modified = original + increment;
        return "Original: " + original + ", Modified: " + modified;
    }

    @Override
    public String demonstrateLocalDateImmutability(LocalDate original, int daysToAdd) {
        LocalDate modified = original.plusDays(daysToAdd);
        return "Original: " + original + ", Modified: " + modified;
    }

    @Override
    public ImmutablePerson createImmutablePerson(String name, int age) {
        return ImmutablePerson.builder().name(name).age(age).build();
    }

    @Override
    public CompletableFuture<String> fetchCombinedData() {
        return dataFetcher.fetchDataFromSource1()
                .thenCombine(dataFetcher.fetchDataFromSource2(), (data1, data2) -> data1 + " | " + data2);
    }

    @Override
    public int sumUsingForkJoin(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new SumTask(array, 0, array.length));
    }

    @Override
    public int sumUsingTraditionalThreads(int[] array) throws InterruptedException {
        int numThreads = 2;
        Thread[] threads = new Thread[numThreads];
        int[] partialSums = new int[numThreads];
        int chunkSize = (int) Math.ceil(array.length / (double) numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                int start = index * chunkSize;
                int end = Math.min((index + 1) * chunkSize, array.length);
                for (int j = start; j < end; j++) {
                    partialSums[index] += array[j];
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int totalSum = 0;
        for (int partialSum : partialSums) {
            totalSum += partialSum;
        }

        return totalSum;
    }

    public List<String> scrapeWebsites(String[] urls) throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>();

        // Submit each URL as a separate task
        for (String url : urls) {
            futures.add(executorService.submit(new WebScrapingTask(url)));
        }

        // Collect results
        List<String> results = new ArrayList<>();
        for (Future<String> future : futures) {
            results.add(future.get()); // Blocking call to get each result
        }

        return results;
    }

    @Override
    public String hashPassword(String password) {
        try {
            // Generate a secure random salt
            SecureRandom random = new SecureRandom();
            byte[] saltBytes = new byte[saltLength];
            random.nextBytes(saltBytes);
            String salt = Base64.getEncoder().encodeToString(saltBytes);

            // Hash the password using HASHING_ALGORITHM with salt and iterations
            MessageDigest messageDigest = MessageDigest.getInstance(hashingAlgorithm);
            String combined = salt + password;

            byte[] hashedBytes = combined.getBytes();
            // Perform multiple iterations (key stretching)
            for (int i = 0; i < hashIterations; i++) {
                hashedBytes = messageDigest.digest(hashedBytes);
            }

            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password: " + e.getMessage());
        }
    }

    @Override
    public String generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionAlgorithm);
            keyGenerator.init(keySize);
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    @Override
    public String encrypt(String plainText, String secretKey) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(secretKey);
            SecretKeySpec keySpec = new SecretKeySpec(decodedKey, encryptionAlgorithm);

            Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting text", e);
        }
    }

    @Override
    public String decrypt(String encryptedText, String secretKey) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(secretKey);
            SecretKeySpec keySpec = new SecretKeySpec(decodedKey, encryptionAlgorithm);

            Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting text", e);
        }
    }
}
