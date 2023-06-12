package com.hacheery.dailychallenger.service;

import com.hacheery.dailychallenger.entity.CompletedTask;

/**
 * Created by HachNV on 12/06/2023
 */
public interface CompletedTaskService {
    CompletedTask markAsCompleted(Long taskId);
}
