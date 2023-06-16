package com.hacheery.dailychallenger.repository;

import com.hacheery.dailychallenger.entity.RandomTask;
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
    @Query(value = "SELECT rt.* FROM random_task rt LIMIT 3", nativeQuery = true)
    List<Task> getTopFiveRecords();

    @Query(value = "SELECT t.task_id FROM ( SELECT (SELECT t.task_id FROM task AS t WHERE t.category_id = c.category_id ORDER BY RAND() LIMIT 1) AS taskId FROM category AS c ORDER BY RAND() LIMIT 5) AS randomTasks JOIN task AS t ON randomTasks.taskId = t.task_id", nativeQuery = true)
    List<RandomTask> createListRandomTasks();

    @Query(value = "SELECT * FROM task WHERE category_id = :categoryId ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Task findRandomTaskByCategory(@Param("categoryId") Long categoryId);

}
