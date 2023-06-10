package com.hacheery.dailychallenger.service.impl;

import com.hacheery.dailychallenger.entity.Task;
import com.hacheery.dailychallenger.exception.ResourceNotFoundException;
import com.hacheery.dailychallenger.repository.TaskRepository;
import com.hacheery.dailychallenger.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by HachNV on 10/06/2023
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task createTasks(Task task) {
        validateTask(task);
        try {
            return taskRepository.save(task);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi lưu nhiệm vụ vào cơ sở dữ liệu", ex);
        }
    }

    @Override
    @Transactional
    public Task updateTasks(Task task, Long taskId) {
        validateTaskId(taskId);
        validateTask(task);

        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhiệm vụ với tên: " + task.getTaskName()));
        existingTask.setTaskName(task.getTaskName());
        existingTask.setTaskDescription(task.getTaskDescription());
        existingTask.setTaskReward(task.getTaskReward());

        try {
            return taskRepository.save(existingTask);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi lưu nhiệm vụ vào cơ sở dữ liệu", ex);
        }
    }

    @Override
    public Task getTasksById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task", "Id", taskId)
        );
    }

    @Override
    public void deleteTasks(Long taskId) {
        validateTaskId(taskId);

        try {
            taskRepository.deleteById(taskId);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi xóa nhiệm vụ khỏi cơ sở dữ liệu", ex);
        }
    }

    // logic function from here

    private void validateTask(Task task) {
        Objects.requireNonNull(task, "Thông tin nhiệm vụ không được để trống");
        validateTaskName(task.getTaskName());
    }

    private void validateTaskName(String taskName) {
        // thay vì kiểm tra xem task.getName() == null && task.getName().isEmpty() không
        // có thể dùng hàm có sẵn StringUtils.isBlank(task.getName) để đơn giản hóa
        if (StringUtils.isBlank(taskName)) {
            throw new IllegalArgumentException("Tên nhiệm vụ không được để trống");
        }
        if (taskRepository.existByTaskName(taskName)) {
            throw new IllegalArgumentException("Tên nhiệm vụ đã tồn tại");
        }
    }
    private void validateTaskId(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Category", "Id", taskId);
        }
    }
}
