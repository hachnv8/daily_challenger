package com.hacheery.dailychallenger.service;


import com.hacheery.dailychallenger.entity.Category;

/**
 * Created by HachNV on 17/04/2023
 */
public interface CategoryService {
    //PagedResponse<Category> getCategories(CategoryRequest request);
    Category getCategory(Long categoryId);
    Category createCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
    void deleteCategory(Long categoryId);
}
