package com.personal.javastudy.service.serviceImpl;

import com.personal.javastudy.dtos.function_interface.OperationRequest;
import com.personal.javastudy.dtos.function_interface.OperationResponse;
import com.personal.javastudy.dtos.function_interface.StringFilterRequest;
import com.personal.javastudy.dtos.memory_types.ImmutablePerson;
import com.personal.javastudy.service.serviceImpl.concurrent_programming.DataFetcher;
import com.personal.javastudy.service.serviceImpl.concurrent_programming.WebScrapingTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JavaStudyServiceImplTest {
    @MockBean
    private DataFetcher dataFetcher;

    @MockBean
    private ExecutorService executorService;
    @Autowired
    private JavaStudyServiceImpl javaStudyService;

    @Test
    void testPerformOperation_Addition() {
        OperationRequest request = OperationRequest.builder()
                .operation("add").number1(5.0).number2(3.0).build();
        OperationResponse response = javaStudyService.performOperation(request);

        assertEquals(8.0, response.getResult(), "Addition operation failed.");
    }

    @Test
    void testPerformOperation_DivisionByZero() {
        OperationRequest request = OperationRequest.builder()
                .operation("divide").number1(5.0).number2(0.0).build();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> javaStudyService.performOperation(request));

        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    void testPerformOperation_UnsupportedOperation() {
        OperationRequest request = OperationRequest.builder()
                .operation("modulo").number1(5.0).number2(3.0).build();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            javaStudyService.performOperation(request));

        assertTrue(exception.getMessage().contains("Unsupported operation"));
    }

    @Test
    void testFilterStrings_StartWith() {
        StringFilterRequest request = StringFilterRequest.builder()
                .strings(Arrays.asList("test", "hello", "team"))
                .filterType("startWith")
                .filterValue("te").build();
        List<String> results = javaStudyService.filterStrings(request);

        assertEquals(2, results.size(), "Filter strings by 'startWith' failed.");
        assertTrue(results.contains("test"));
        assertTrue(results.contains("team"));
    }

    @Test
    void testFilterStrings_UnsupportedFilter() {
        StringFilterRequest request = StringFilterRequest.builder()
                .strings(Arrays.asList("test", "hello", "team"))
                .filterType("endWith")
                .filterValue("te").build();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            javaStudyService.filterStrings(request));

        assertTrue(exception.getMessage().contains("Unsupported filterStrings"));
    }

    @Test
    void testConsumeHeap() {
        // Simulate heap consumption
        assertDoesNotThrow(() -> javaStudyService.consumeHeap(10)); // Allocate 10 MB
        assertDoesNotThrow(() -> javaStudyService.consumeHeap(-1)); // Clear memory
        assertDoesNotThrow(() -> javaStudyService.consumeHeap(-2)); // Trigger GC
    }

    @Test
    void testCreateMemoryLeak() {
        // Simulate memory leak
        assertDoesNotThrow(() -> javaStudyService.createMemoryLeak(1000));

    }

    @Test
    void testClearMemoryLeak() {
        // Simulate clearing memory leak
        assertDoesNotThrow(() -> javaStudyService.createMemoryLeak(1000));
        assertDoesNotThrow(() -> javaStudyService.clearMemoryLeak());
    }

    @Test
    void testDemonstrateStringImmutability() {
        String original = "Hello";
        String appendText = " World";

        String result = javaStudyService.demonstrateStringImmutability(original, appendText);

        assertEquals("Original: Hello, Modified: Hello World", result);
    }

    @Test
    void testDemonstrateIntegerImmutability() {
        Integer original = 5;
        int increment = 10;

        String result = javaStudyService.demonstrateIntegerImmutability(original, increment);

        assertEquals("Original: 5, Modified: 15", result);
    }

    @Test
    void testDemonstrateLocalDateImmutability() {
        LocalDate original = LocalDate.of(2024, 12, 25);
        int daysToAdd = 5;

        String result = javaStudyService.demonstrateLocalDateImmutability(original, daysToAdd);

        assertEquals("Original: 2024-12-25, Modified: 2024-12-30", result);
    }

    @Test
    void testCreateImmutablePerson() {
        String name = "Sang";
        int age = 26;

        ImmutablePerson person = javaStudyService.createImmutablePerson(name, age);

        assertEquals("Sang", person.getName());
        assertEquals(26, person.getAge());
    }

    @Test
    void testFetchCombinedData() {
        when(dataFetcher.fetchDataFromSource1()).thenReturn(CompletableFuture.completedFuture("Data1"));
        when(dataFetcher.fetchDataFromSource2()).thenReturn(CompletableFuture.completedFuture("Data2"));

        CompletableFuture<String> result = javaStudyService.fetchCombinedData();

        assertEquals("Data1 | Data2", result.join());
        verify(dataFetcher).fetchDataFromSource1();
        verify(dataFetcher).fetchDataFromSource2();
    }

    @Test
    void testSumUsingForkJoin() {
        int[] array = {1, 2, 3, 4, 5};
        int result = javaStudyService.sumUsingForkJoin(array);

        assertEquals(15, result);
    }

    @Test
    void testSumUsingTraditionalThreads() throws InterruptedException {
        int[] array = {1, 2, 3, 4, 5};
        int result = javaStudyService.sumUsingTraditionalThreads(array);

        assertEquals(15, result);
    }

    @Test
    void testScrapeWebsites() throws ExecutionException, InterruptedException {
        String[] urls = {"https://example.com", "https://test.com"};

        Future<String> future1 = mock(Future.class);
        Future<String> future2 = mock(Future.class);

        when(executorService.submit(any(WebScrapingTask.class))).thenReturn(future1, future2);
        when(future1.get()).thenReturn("Result from example.com");
        when(future2.get()).thenReturn("Result from test.com");

        List<String> results = javaStudyService.scrapeWebsites(urls);

        assertEquals(2, results.size());
        assertEquals("Result from example.com", results.get(0));
        assertEquals("Result from test.com", results.get(1));
        verify(executorService, times(2)).submit(any(WebScrapingTask.class));
        verify(future1).get();
        verify(future2).get();
    }

    @Test
    public void testHashPasswordNotNull() {
        String password = "MySecurePassword";

        String hash = javaStudyService.hashPassword(password);

        // Validate that the hash is not null or empty
        assertNotNull(hash, "Hashed password should not be null");
        assertFalse(hash.isEmpty(), "Hashed password should not be empty");
    }

    @Test
    public void testHashPasswordConsistency() {
        String password = "MySecurePassword";

        String hash1 = javaStudyService.hashPassword(password);
        String hash2 = javaStudyService.hashPassword(password);

        // Validate that each hash is different due to a unique salt
        assertNotEquals(hash1, hash2, "Hashes should be unique for the same password");
    }

    @Test
    public void testHashPasswordLength() {
        String password = "MySecurePassword";

        String hash = javaStudyService.hashPassword(password);

        // Validate that the hash has a reasonable length
        assertTrue(hash.length() > 20, "Hash length should be greater than 20 characters");
    }

    @Test
    void testGenerateSecretKey() {
        String secretKey = javaStudyService.generateSecretKey();

        // Assert that the secret key is not null or empty
        assertNotNull(secretKey);
        assertFalse(secretKey.isEmpty());

        // Optionally, check if the base64 encoding is valid
        assertDoesNotThrow(() -> Base64.getDecoder().decode(secretKey));
    }

    @Test
    void testEncrypt() {
        String plainText = "Hello, World!";
        String secretKey = javaStudyService.generateSecretKey(); // Use a generated key

        // Encrypt the text
        String encryptedText = javaStudyService.encrypt(plainText, secretKey);

        // Assert that encrypted text is not the same as plain text
        assertNotEquals(plainText, encryptedText);
        assertNotNull(encryptedText);

        // Optionally, check if the encrypted text is in base64 format
        assertDoesNotThrow(() -> Base64.getDecoder().decode(encryptedText));
    }

    @Test
    void testDecrypt() {
        String plainText = "Hello, World!";
        String secretKey = javaStudyService.generateSecretKey(); // Use a generated key

        // First, encrypt the text
        String encryptedText = javaStudyService.encrypt(plainText, secretKey);

        // Now decrypt the text
        String decryptedText = javaStudyService.decrypt(encryptedText, secretKey);

        // Assert that the decrypted text matches the original plain text
        assertEquals(plainText, decryptedText);
    }
}
