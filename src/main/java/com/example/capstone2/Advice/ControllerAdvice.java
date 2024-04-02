package com.example.capstone2.Advice;


//Controller package always return 200

//always return 400

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.ApiResponse.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ControllerAdvice {


    Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    //add Logger to all controllers
    //my exception in service "custom exception"
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException error) {
        String message = error.getMessage();
        logger = LoggerFactory.getLogger("Api exception: " + message);
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }


    //validation exception related to @valid ex adding negative number to price where price should be positive.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException error) {
        String message = error.getFieldError().getDefaultMessage();
        logger = LoggerFactory.getLogger("MethodArgumentNotValidException: " + message);

        return ResponseEntity.status(400).body(new ApiResponse(message));
    }


    //SQL Data Integrity ex when adding duplicated email where email is unique
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException error) {
        String message = error.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //when parsing invalid json value type
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException error){
        String message = error.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //Type mismatch ex assigning String to integer variable
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException error){
        String message = error.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //handles Not supported media type ex sending html or text when expecting json,
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException error){
        String message = error.getMessage();
        String errorCode = error.getTypeMessageCode();
        return ResponseEntity.status(400).body(new ApiResponse(message+"\n"+errorCode));
    }

    //requesting endpoint that does not exists
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException error){
        String message = error.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //requesting invalid endpoint type ex requesting get endpoint by request method post
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException error){
        String message = error.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

}