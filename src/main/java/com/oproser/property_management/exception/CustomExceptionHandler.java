package com.oproser.property_management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handlerFieldValidation(MethodArgumentNotValidException ex) {
        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = null;
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            logger.info("Inside field validation - level info: {} - {}", fieldError.getField(),
                    fieldError.getDefaultMessage());
            logger.debug("Inside field validation - level debug: {} - {}", fieldError.getField(),
                    fieldError.getDefaultMessage());
            errorModel = new ErrorModel();
            errorModel.setCode(fieldError.getField());
            errorModel.setMessage(fieldError.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBussinessException(final BusinessException ex) {
        for (ErrorModel em : ex.getErrors()) {
            logger.info("Business exception is thrown - level info: {} - {}", em.getCode(), em.getMessage());
            logger.debug("Business exception is thrown - level debug: {} - {}", em.getCode(),
                    em.getMessage());
            logger.warn("Business exception is thrown - level warn: {} - {}", em.getCode(), em.getMessage());
            logger.error("Business exception is thrown - level error: {} - {}", em.getCode(),
                    em.getMessage());
        }

        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
