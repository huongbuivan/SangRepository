package com.personal.javastudy.controllers;

import com.personal.javastudy.dtos.function_interface.OperationRequest;
import com.personal.javastudy.dtos.function_interface.OperationResponse;
import com.personal.javastudy.dtos.function_interface.StringFilterRequest;
import com.personal.javastudy.service.JavaStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/function-interface/")
@RequiredArgsConstructor
public class FunctionInterfaceController {

    private final JavaStudyService javaStudyservice;

    @PostMapping("/calculate")
    public ResponseEntity<OperationResponse> calculate(
            @RequestBody OperationRequest request) {
        return ResponseEntity.ok(javaStudyservice.performOperation(request));
    }

    @PostMapping("/filter-strings")
    public ResponseEntity<List<String>> filterStrings(
            @RequestBody StringFilterRequest request) {
        List<String> results = javaStudyservice.filterStrings(request);
        return ResponseEntity.ok(results);
    }
}
