package com.hacheery.dailychallenger.exception;

import com.hacheery.dailychallenger.payload.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by HachNV on 19/04/2023
 */
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> resolveException(ResourceNotFoundException exception) {
        ApiResponse apiResponse = exception.getApiResponse();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        String message = exception.getMessage();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(message);

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
