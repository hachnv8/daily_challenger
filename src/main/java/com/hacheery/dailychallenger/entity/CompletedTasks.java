package com.hacheery.dailychallenger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

/**
 * Created by HachNV on 10/06/2023
 */
@Entity
@Data
public class CompletedTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long completedId;

    private long taskId;

    private Date completionDate;

    private long userId;

}
