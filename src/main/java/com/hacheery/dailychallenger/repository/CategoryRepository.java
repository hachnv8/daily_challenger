package com.hacheery.dailychallenger.repository;

import com.hacheery.dailychallenger.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by HachNV on 17/04/2023
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    boolean existsByCategoryName(String name);

    @Query(value = "SELECT * FROM category WHERE category_id IN (SELECT DISTINCT category_id FROM task) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Category findRandomCategoryWithTasks();
}
