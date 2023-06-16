package com.hacheery.dailychallenger.service.impl;

import com.hacheery.dailychallenger.entity.RandomTask;
import com.hacheery.dailychallenger.entity.Task;
import com.hacheery.dailychallenger.repository.RandomTaskRepository;
import com.hacheery.dailychallenger.repository.TaskRepository;
import com.hacheery.dailychallenger.security.config.JwtService;
import com.hacheery.dailychallenger.service.RandomTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<RandomTask> createRandomTasks(int numberOfTasks) {
        List<Task> taskList = taskRepository.findAll();
        Map<Long, List<Task>> taskMap = new HashMap<>();
        for (Task task : taskList) {
            Long categoryId = task.getCategoryId();
            taskMap.computeIfAbsent(categoryId, k -> new ArrayList<>()).add(task);
        }
        List<RandomTask> random = new ArrayList<>();
        List<Task> randomTasks = generateRandomNumbers(5, taskMap);
        for (Task task: randomTasks) {
               RandomTask rt = new RandomTask();
               rt.setTaskId(task.getTaskId());
               rt.setCompleted(false);
               //rt.setReceiveDate(Instant.from(LocalDate.now()));
               rt.setUserId(jwtService.getUserInformation().getUserId());
               random.add(rt);
        }
        return randomTaskRepository.saveAll(random);
    }


    public List<Task> generateRandomNumbers(int n, Map<Long, List<Task>> m) {
        List<Task> randomTasks = new ArrayList<>();
        Random random = new Random();
        Set<Long> selectedSublists = new HashSet<>();

        int count = 0;
        while(count < m.size() && randomTasks.size() < n) {
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
}
