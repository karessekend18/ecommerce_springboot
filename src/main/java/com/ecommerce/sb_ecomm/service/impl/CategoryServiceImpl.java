package com.ecommerce.sb_ecomm.service.impl;


import com.ecommerce.sb_ecomm.dto.CategoryRequest;
import com.ecommerce.sb_ecomm.dto.CategoryResponse;
import com.ecommerce.sb_ecomm.exceptions.APIExceptions;
import com.ecommerce.sb_ecomm.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecomm.model.Category;
import com.ecommerce.sb_ecomm.repository.CategoryRepository;
import com.ecommerce.sb_ecomm.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty())
            throw new APIExceptions("No category created till now");
        List<CategoryRequest> categoryRequests = categories.stream()
                .map(category -> modelMapper.map(category, CategoryRequest.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryRequests);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryRequest createCategory(CategoryRequest categoryRequest) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        Category categoryFromDb = categoryRepository.findByCategoryName(categoryRequest.getCategoryName());
        if(categoryFromDb != null) {
            throw new APIExceptions("Category with name " + categoryRequest.getCategoryName() + " already exists!");
        }
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryRequest.class);
    }

    @Override
    public CategoryRequest deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));


        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryRequest.class);
    }

    @Override
    public CategoryRequest updateCategory(CategoryRequest categoryRequest, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Category category = modelMapper.map(categoryRequest, Category.class);
        categoryRequest.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryRequest.class);


    }

}
