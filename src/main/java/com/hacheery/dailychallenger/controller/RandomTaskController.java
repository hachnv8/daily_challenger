package com.hacheery.dailychallenger.controller;

import com.hacheery.dailychallenger.entity.RandomTask;
import com.hacheery.dailychallenger.payload.response.ApiResponse;
import com.hacheery.dailychallenger.service.impl.RandomTaskServiceImpl;
import com.hacheery.dailychallenger.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by HachNV on 12/06/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/random")
public class RandomTaskController {
    private final RandomTaskServiceImpl randomTaskService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<List<RandomTask>>> createFiveRandomTasks() {
        ApiResponse<List<RandomTask>> response = new ApiResponse<>();
        response.setSuccess(true);

        response.setMessage("Tạo thành công danh mục nhiệm vụ cho ngày: " + DateUtils.getDateYYYYMMDD(LocalDate.now()));
        response.setData(randomTaskService.createRandomList());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
