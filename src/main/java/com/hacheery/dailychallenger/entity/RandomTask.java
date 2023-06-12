package com.hacheery.dailychallenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Created by HachNV on 12/06/2023
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RandomTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long randomTaskId;

    private Long taskId;

    private Instant receiveDate;

    private Long userId;

    private boolean isCompleted;

    private Instant completeDate;
}
