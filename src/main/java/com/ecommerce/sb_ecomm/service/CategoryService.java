package com.ecommerce.sb_ecomm.service;

import com.ecommerce.sb_ecomm.dto.CategoryRequest;
import com.ecommerce.sb_ecomm.dto.CategoryResponse;
import com.ecommerce.sb_ecomm.model.Category;


public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryRequest createCategory(CategoryRequest categoryRequest);

    CategoryRequest deleteCategory(Long categoryId);

    CategoryRequest updateCategory(CategoryRequest categoryRequest, Long categoryId);
}
