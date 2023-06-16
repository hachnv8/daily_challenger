package com.hacheery.dailychallenger.service;

import com.hacheery.dailychallenger.entity.DailyTask;

import java.util.List;

/**
 * Created by HachNV on 16/06/2023
 */
public interface DailyService {
    List<DailyTask> createDailyTasks(int numberOfTasks);
    DailyTask markAsCompleted(Long taskId);
}
