package com.hacheery.dailychallenger.controller;

import com.hacheery.dailychallenger.entity.Task;
import com.hacheery.dailychallenger.service.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HachNV on 10/06/2023
 */
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTasksById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
