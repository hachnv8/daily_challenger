package com.hacheery.dailychallenger.service;


import com.hacheery.dailychallenger.entity.Task;

/**
 * Created by HachNV on 10/06/2023
 */
public interface TaskService {
    Task createTasks(Task task);
    Task updateTasks(Task task, Long taskId);
    Task getTasksById(Long taskId);
    void deleteTasks(Long taskId);
}
