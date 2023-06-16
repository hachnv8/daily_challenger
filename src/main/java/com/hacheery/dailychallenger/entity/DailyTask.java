package com.hacheery.dailychallenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by HachNV on 10/06/2023
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyTaskId;

    private Long taskId;

    private LocalDate taskDate;

    private Long userId;

    private LocalDate completedDate;

    private boolean isCompleted;
}
