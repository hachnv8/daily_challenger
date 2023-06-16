package com.hacheery.dailychallenger.controller;

import com.hacheery.dailychallenger.entity.Category;
import com.hacheery.dailychallenger.payload.response.ApiResponse;
import com.hacheery.dailychallenger.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HachNV on 17/04/2023
 */
@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody Category category) {
        ApiResponse<Category> response = new ApiResponse<>();
        Category savedCategory = categoryService.createCategory(category);
        // Log the successful creation
        logger.info("Tạo thành công category với ID: " + savedCategory.getCategoryId());
        response.setSuccess(true);
        response.setMessage("Tạo category thành công!");
        response.setData(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(Category category, @PathVariable Long categoryId) {
        // cần check xem ở đây nếu lấy trực tiếp id từ category có được không, hay cần phải lấy @PathVariable Long categoryId
        // đối với rest api, để thuận tiện cho người dùng thì khi click vào 1 sản phẩm ta có link như sau:
        // http://localhost:8080/book/9783827319333
        // lúc này nên sử dụng @PathVariable để hứng id
        ApiResponse<Category> response = new ApiResponse<>();
        Category updatedCategory = categoryService.updateCategory(category, categoryId);
        logger.info("Cập nhật thành công category với ID: " + categoryId);
        response.setSuccess(true);
        response.setMessage("Cập nhật category thành công!");
        response.setData(updatedCategory);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        logger.info("Xóa thành công category với ID: " + categoryId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Xóa danh mục thành công");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
