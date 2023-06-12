package com.hacheery.dailychallenger.service.impl;

import com.hacheery.dailychallenger.entity.CompletedTask;
import com.hacheery.dailychallenger.exception.ResourceNotFoundException;
import com.hacheery.dailychallenger.repository.CompletedRepository;
import com.hacheery.dailychallenger.security.config.JwtService;
import com.hacheery.dailychallenger.service.CompletedTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Created by HachNV on 12/06/2023
 */
@Service
@RequiredArgsConstructor
public class CompletedTaskServiceImpl implements CompletedTaskService {
    private final CompletedRepository completedRepository;
    private final JwtService jwtService;
    
    @Override
    public CompletedTask markAsCompleted(Long taskId) {
        validateTaskId(taskId);
        CompletedTask task = new CompletedTask();
        task.setTaskId(taskId);
        task.setUserId(jwtService.getUserName());
        task.setCompleteDate(Instant.now());
        try {
            return completedRepository.save(task);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi update trạng thái nhiệm vụ");
        }
    }


    // logic function from here
    private void validateTaskId(Long taskId) {
        if (!completedRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Category", "Id", taskId);
        }
    }
}
