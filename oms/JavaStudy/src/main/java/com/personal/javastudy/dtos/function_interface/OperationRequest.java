package com.personal.javastudy.dtos.function_interface;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OperationRequest {
    private double number1;
    private double number2;
    private String operation; // add, subtract, multiply, divide
}
