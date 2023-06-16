package com.hacheery.dailychallenger.repository;

import com.hacheery.dailychallenger.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by HachNV on 10/06/2023
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTaskName(String taskName);
}
