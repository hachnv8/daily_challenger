package com.hacheery.dailychallenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Created by HachNV on 10/06/2023
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long completedId;

    private long taskId;

    private Instant completeDate;

    private long userId;

}
