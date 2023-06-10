package com.hacheery.dailychallenger.repository;

import com.hacheery.dailychallenger.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HachNV on 10/06/2023
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existByTaskName(String taskName);
}
