package com.personal.javastudy.service.serviceImpl;

import com.personal.javastudy.dtos.function_interface.OperationRequest;
import com.personal.javastudy.dtos.function_interface.OperationResponse;
import com.personal.javastudy.dtos.function_interface.StringFilterRequest;
import com.personal.javastudy.dtos.memory_types.ImmutablePerson;
import com.personal.javastudy.service.JavaStudyService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JavaStudyServiceImpl implements JavaStudyService {

    private final Map<String, Calculator> operations = new HashMap<>();
    private final Map<String, StringCondition> filterStrings = new HashMap<>();
    private List<byte[]> memoryConsumers = new ArrayList<>();
    private static final Map<Integer, String> LEAK_MAP = new HashMap<>();

    public JavaStudyServiceImpl() {
        operations.put("add", (a, b) -> a + b);
        operations.put("subtract", (a, b) -> a - b);
        operations.put("multiply", (a, b) -> a * b);
        operations.put("divide", (a, b) -> {
            if (b == 0) throw new IllegalArgumentException("Division by zero is not allowed");
            return a / b;
        });

        filterStrings.put("startWith", (str, filterValue) -> str.startsWith(filterValue));
        filterStrings.put("contains", (str, filterValue) -> str.contains(filterValue));
    }

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
            LEAK_MAP.put(i, "leaking data " + System.nanoTime());
        }
    }

    @Override
    public void clearMemoryLeak() {
        LEAK_MAP.clear();
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
}
