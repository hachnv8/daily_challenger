package com.hacheery.dailychallenger.service.impl;

import com.hacheery.dailychallenger.entity.DailyTask;
import com.hacheery.dailychallenger.entity.Task;
import com.hacheery.dailychallenger.exception.ResourceNotFoundException;
import com.hacheery.dailychallenger.repository.DailyTaskRepository;
import com.hacheery.dailychallenger.repository.TaskRepository;
import com.hacheery.dailychallenger.security.config.JwtService;
import com.hacheery.dailychallenger.service.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by HachNV on 16/06/2023
 */
@Service
@RequiredArgsConstructor
public class DailyTaskServiceImpl implements DailyService {
    private final DailyTaskRepository dailyTaskRepository;
    private final TaskRepository taskRepository;
    private final JwtService jwtService;

    @Override
    public List<DailyTask> createDailyTasks(int numberOfTasks) {
        List<Task> taskList = taskRepository.findAll();
        Map<Long, List<Task>> taskMap = new HashMap<>();
        for (Task task : taskList) {
            Long categoryId = task.getCategoryId();
            taskMap.computeIfAbsent(categoryId, k -> new ArrayList<>()).add(task);
        }
        List<DailyTask> random = new ArrayList<>();
        List<Task> dailyTasks = generateDailyTasks(5, taskMap);
        for (Task task : dailyTasks) {
            DailyTask rt = new DailyTask();
            rt.setTaskId(task.getTaskId());
            rt.setCompleted(false);
            rt.setTaskDate(LocalDate.now());
            //rt.setReceiveDate(Instant.from(LocalDate.now()));
            rt.setUserId(jwtService.getUserInformation().getUserId());
            random.add(rt);
        }
        return dailyTaskRepository.saveAll(random);
    }

    @Override
    public DailyTask markAsCompleted(Long taskId) {
        validateTaskId(taskId);
        DailyTask task = dailyTaskRepository.findById(taskId).orElseThrow(
                () -> new IllegalArgumentException("Không tìm thấy nhiệm vụ với ID: " + taskId)
        );
        task.setCompleted(true);
        task.setCompletedDate(LocalDate.now());

        try {
            return dailyTaskRepository.save(task);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi update trạng thái nhiệm vụ");
        }
    }

    public List<Task> generateDailyTasks(int n, Map<Long, List<Task>> m) {
        List<Task> randomTasks = new ArrayList<>();
        Random random = new Random();
        Set<Long> selectedSublists = new HashSet<>();

        int count = 0;
        while (count < m.size() && randomTasks.size() < n) {
            List<Long> keysAsArray = new ArrayList<>(m.keySet());
            Long randomSublistIndex = keysAsArray.get(random.nextInt(keysAsArray.size()));
            if (selectedSublists.contains(randomSublistIndex)) {
                continue; // Skip if sublist was already selected
            }
            List<Task> sublist = m.get(randomSublistIndex);
            // Pick a random element from the sublist if it has at least one element
            if (sublist.size() > 0) {
                List<Task> sublistCopy = new ArrayList<>(sublist); // Create a mutable copy
                int randomIndex = random.nextInt(sublistCopy.size());
                randomTasks.add(sublistCopy.get(randomIndex));
                sublistCopy.remove(randomIndex);
                m.replace(randomSublistIndex, sublistCopy);
            }
            count++;
            selectedSublists.add(randomSublistIndex);
        }

        selectedSublists.clear();
        int decrementCount = 0;
        while (decrementCount < m.size() && randomTasks.size() < n) {
            List<Long> keysAsArray = new ArrayList<>(m.keySet());
            Long randomSublistIndex = keysAsArray.get(random.nextInt(keysAsArray.size()));
            if (selectedSublists.contains(randomSublistIndex)) {
                continue; // Skip if sublist was already selected
            }
            List<Task> sublist = m.get(randomSublistIndex);

            if (sublist.size() > 0) {
                List<Task> sublistCopy = new ArrayList<>(sublist); // Create a mutable copy
                int randomIndex = random.nextInt(sublistCopy.size());
                randomTasks.add(sublistCopy.get(randomIndex));
                sublistCopy.remove(randomIndex);
                m.replace(randomSublistIndex, sublistCopy);
            }
            selectedSublists.add(randomSublistIndex);
            decrementCount++;
        }
        return randomTasks;
    }

    // logic function from here
    private void validateTaskId(Long taskId) {
        if (!dailyTaskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Category", "Id", taskId);
        }
    }
}
