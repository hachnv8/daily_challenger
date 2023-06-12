package com.hacheery.dailychallenger.service.impl;

import com.hacheery.dailychallenger.entity.RandomTask;
import com.hacheery.dailychallenger.entity.Task;
import com.hacheery.dailychallenger.repository.RandomTaskRepository;
import com.hacheery.dailychallenger.repository.TaskRepository;
import com.hacheery.dailychallenger.security.config.JwtService;
import com.hacheery.dailychallenger.service.RandomTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HachNV on 12/06/2023
 */
@Service
@RequiredArgsConstructor
public class RandomTaskServiceImpl implements RandomTaskService {
    private final RandomTaskRepository randomTaskRepository;
    private final TaskRepository taskRepository;
    private final JwtService jwtService;

    @Override
    public List<RandomTask> createRandomList() {
        List<RandomTask> list = new ArrayList<>();
        List<Task> taskList = taskRepository.getTopFiveRecords();
        for (Task task: taskList) {
            RandomTask randomTask = new RandomTask();
            randomTask.setTaskId(task.getTaskId());
            randomTask.setUserId(jwtService.getUserName());
            list.add(randomTask);
        }
        return randomTaskRepository.saveAll(list);
    }
}
