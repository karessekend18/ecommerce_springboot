package com.ecommerce.sb_ecomm.controller;

import com.ecommerce.sb_ecomm.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class CategoryController {

    private List<Category> categories = new ArrayList<>();

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categories;
    }

    @PostMapping("/categories")
    public String createCategory(@RequestBody Category category){
        categories.add(category);
        return "Category added successfully!";
    }
}
