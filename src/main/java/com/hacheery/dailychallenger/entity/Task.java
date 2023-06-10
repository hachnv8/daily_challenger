package com.hacheery.dailychallenger.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Created by HachNV on 10/06/2023
 */
@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @NotBlank
    private String taskName;

    private String taskDescription;

    private String taskReward;
}
