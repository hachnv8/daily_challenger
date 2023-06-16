package com.hacheery.dailychallenger.controller;

import com.hacheery.dailychallenger.entity.DailyTask;
import com.hacheery.dailychallenger.payload.response.ApiResponse;
import com.hacheery.dailychallenger.service.impl.DailyTaskServiceImpl;
import com.hacheery.dailychallenger.util.DateUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by HachNV on 16/06/2023
 */
@RestController
@RequestMapping("daily_task")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DailyTaskController {
    private final DailyTaskServiceImpl dailyTaskService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<List<DailyTask>>> createFiveRandomTasks() {
        ApiResponse<List<DailyTask>> response = new ApiResponse<>();
        response.setSuccess(true);

        response.setMessage("Tạo thành công danh mục nhiệm vụ cho ngày: " + DateUtils.getDateYYYYMMDD(LocalDate.now()));
        response.setData(dailyTaskService.createDailyTasks(5));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/markAsCompleted/{taskId}")
    public ResponseEntity<ApiResponse<DailyTask>> markAsCompleted(@PathVariable Long taskId) {
        ApiResponse<DailyTask> response = new ApiResponse<>();
        DailyTask completedTask = dailyTaskService.markAsCompleted(taskId);
        response.setSuccess(true);
        response.setMessage("Cập nhật nhiệm vụ thành công");
        response.setData(completedTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
