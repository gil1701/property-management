package com.oproser.property_management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private List<ErrorModel> errors;
}
