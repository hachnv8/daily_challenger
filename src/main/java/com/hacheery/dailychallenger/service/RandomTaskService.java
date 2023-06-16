package com.hacheery.dailychallenger.service;

import com.hacheery.dailychallenger.entity.RandomTask;

import java.util.List;

/**
 * Created by HachNV on 12/06/2023
 */
public interface RandomTaskService {
    List<RandomTask> createRandomTasks(int numberOfTasks);
}
