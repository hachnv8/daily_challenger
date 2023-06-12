package com.hacheery.dailychallenger.repository;

import com.hacheery.dailychallenger.entity.CompletedTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HachNV on 12/06/2023
 */
public interface CompletedRepository extends JpaRepository<CompletedTask, Long> {
}
