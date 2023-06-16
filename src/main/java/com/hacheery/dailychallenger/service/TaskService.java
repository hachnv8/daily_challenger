package com.hacheery.dailychallenger.service;


import com.hacheery.dailychallenger.entity.Task;

/**
 * Created by HachNV on 10/06/2023
 */
public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Task task, Long taskId);
    Task getTasksById(Long taskId);
    void deleteTask(Long taskId);

}
