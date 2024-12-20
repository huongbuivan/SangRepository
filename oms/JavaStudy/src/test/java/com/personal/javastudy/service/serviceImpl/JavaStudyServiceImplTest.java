package com.personal.javastudy.service.serviceImpl;

import com.personal.javastudy.dtos.function_interface.OperationRequest;
import com.personal.javastudy.dtos.function_interface.OperationResponse;
import com.personal.javastudy.dtos.function_interface.StringFilterRequest;
import com.personal.javastudy.dtos.memory_types.ImmutablePerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JavaStudyServiceImplTest {

    private JavaStudyServiceImpl javaStudyService;

    @BeforeEach
    void setup() {
        javaStudyService = new JavaStudyServiceImpl();
    }

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
}
