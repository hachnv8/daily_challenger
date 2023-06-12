package com.hacheery.dailychallenger.service.impl;

import com.hacheery.dailychallenger.entity.Category;
import com.hacheery.dailychallenger.exception.ResourceNotFoundException;
import com.hacheery.dailychallenger.repository.CategoryRepository;
import com.hacheery.dailychallenger.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by HachNV on 17/04/2023
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long categoryId) {
        return null;
    }

    @Override
    public Category createCategory(Category category) {
        validateCategory(category);

        try {
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi lưu danh mục vào cơ sở dữ liệu", ex);
        }
    }

    @Override
    @Transactional
    public Category updateCategory(Category category, Long categoryId) {
        validateCategoryId(categoryId);
        validateCategory(category);

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục với ID: " + categoryId));
        existingCategory.setName(category.getName());

        try {
            return categoryRepository.save(existingCategory);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi cập nhật danh mục vào cơ sở dữ liệu", ex);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        validateCategoryId(categoryId);

        try {
            categoryRepository.deleteById(categoryId);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Lỗi xóa danh mục khỏi cơ sở dữ liệu", ex);
        }
    }

    // logic function from here

    private void validateCategory(Category category) {
        Objects.requireNonNull(category, "Thông tin danh mục không được để trống");
        validateCategoryName(category.getName());
    }

    private void validateCategoryName(String categoryName) {
        // thay vì kiểm tra xem category.getName() == null && category.getName().isEmpty() không
        // có thể dùng hàm có sẵn StringUtils.isBlank(category.getName) để đơn giản hóa
        if (StringUtils.isBlank(categoryName)) {
            throw new IllegalArgumentException("Tên danh mục không được để trống");
        }
        if (categoryRepository.existsByName(categoryName)) {
            throw new IllegalArgumentException("Tên danh mục đã tồn tại");
        }
    }

    private void validateCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", "Id", categoryId);
        }
    }
}
