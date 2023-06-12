package com.hacheery.dailychallenger.controller;

import com.hacheery.dailychallenger.entity.CompletedTask;
import com.hacheery.dailychallenger.payload.response.ApiResponse;
import com.hacheery.dailychallenger.service.impl.CompletedTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HachNV on 12/06/2023
 */
@RestController
@RequestMapping("/complete")
@RequiredArgsConstructor
public class CompletedTaskController {
    private final CompletedTaskServiceImpl completedTaskService;

    @PostMapping("/markAsCompleted/{taskId}")
    public ResponseEntity<ApiResponse<CompletedTask>> markAsCompleted(@PathVariable Long taskId) {
        ApiResponse<CompletedTask> response = new ApiResponse<>();
        CompletedTask completedTask = completedTaskService.markAsCompleted(taskId);
        response.setSuccess(true);
        response.setMessage("Cập nhật nhiệm vụ thành công");
        response.setData(completedTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
