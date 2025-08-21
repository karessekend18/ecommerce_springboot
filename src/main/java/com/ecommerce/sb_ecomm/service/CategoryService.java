package com.ecommerce.sb_ecomm.service;

import com.ecommerce.sb_ecomm.dto.CategoryResponse;
import com.ecommerce.sb_ecomm.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
