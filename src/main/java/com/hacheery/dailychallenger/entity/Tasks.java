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
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @NotBlank
    private String task_name;

    private String task_description;

    private String task_reward;
}
