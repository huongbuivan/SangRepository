package com.personal.javastudy.dtos.function_interface;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class StringFilterRequest {
    private List<String> strings;
    private String filterType;
    private String filterValue;
}
