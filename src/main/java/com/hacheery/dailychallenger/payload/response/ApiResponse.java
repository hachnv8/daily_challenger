package com.hacheery.dailychallenger.payload.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by HachNV on 19/04/2023
 */
@Data
@JsonPropertyOrder({
        "success",
        "message"
})
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {
    private static final Long serialize = 1L;
    private boolean success;
    private String message;
    private T data;
}
