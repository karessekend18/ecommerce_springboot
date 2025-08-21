package com.ecommerce.sb_ecomm.service;

import com.ecommerce.sb_ecomm.dto.CategoryRequest;
import com.ecommerce.sb_ecomm.dto.CategoryResponse;
import com.ecommerce.sb_ecomm.model.Category;


public interface CategoryService {

    CategoryResponse getAllCategories();
    CategoryRequest createCategory(CategoryRequest categoryRequest);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
