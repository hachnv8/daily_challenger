package com.hacheery.dailychallenger.exception;

import com.hacheery.dailychallenger.payload.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Created by HachNV on 19/04/2023
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private transient ApiResponse apiResponse;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
