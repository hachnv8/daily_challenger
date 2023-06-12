package com.hacheery.dailychallenger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    private Date taskDate;

    private Long userId;
}
