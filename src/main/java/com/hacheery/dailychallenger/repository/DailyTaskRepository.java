package com.hacheery.dailychallenger.repository;

import com.hacheery.dailychallenger.entity.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HachNV on 16/06/2023
 */
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {
}
