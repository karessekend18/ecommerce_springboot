package com.ecommerce.sb_ecomm.controller;

import com.ecommerce.sb_ecomm.dto.CategoryRequest;
import com.ecommerce.sb_ecomm.dto.CategoryResponse;
import com.ecommerce.sb_ecomm.model.Category;
import com.ecommerce.sb_ecomm.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories() {
        CategoryResponse categoryResponse = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryRequest> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        CategoryRequest savedCategoryRequest = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(savedCategoryRequest, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryRequest> deleteCategory (@PathVariable Long categoryId) {
            CategoryRequest deletedCategory = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryRequest> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest,
                                                 @PathVariable Long categoryId) {
            CategoryRequest savedCategoryRequest = categoryService.updateCategory(categoryRequest, categoryId);
            return new ResponseEntity<>(savedCategoryRequest, HttpStatus.OK);
    }
}
