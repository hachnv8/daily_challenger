package com.hacheery.dailychallenger.repository;

import com.hacheery.dailychallenger.entity.RandomTask;
import com.hacheery.dailychallenger.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by HachNV on 10/06/2023
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTaskName(String taskName);
    @Query(value = "SELECT rt.* FROM RandomTask rt LIMIT 3", nativeQuery = true)
    List<Task> getTopFiveRecords();
}
