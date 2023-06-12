package com.hacheery.dailychallenger.controller;

import com.hacheery.dailychallenger.entity.CompletedTask;
import com.hacheery.dailychallenger.entity.Task;
import com.hacheery.dailychallenger.payload.response.ApiResponse;
import com.hacheery.dailychallenger.service.impl.TaskServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HachNV on 10/06/2023
 */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTasksById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody Task task) {
        ApiResponse<Task> response = new ApiResponse<>();
        Task savedTask = taskService.createTask(task);
        response.setSuccess(true);
        response.setMessage("Tạo nhiệm vụ thành công");
        response.setData(savedTask);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update/{taskId}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@RequestBody Task task, @PathVariable Long taskId) {
        ApiResponse<Task> response = new ApiResponse<>();
        Task updatedTask = taskService.updateTask(task, taskId);
        response.setSuccess(true);
        response.setMessage("Cập nhật nhiệm vụ thành công");
        response.setData(updatedTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<ApiResponse<String>> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Xóa nhiệm vụ thành công");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }


}
