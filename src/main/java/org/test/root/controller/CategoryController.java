package org.test.root.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.test.root.model.Category;
import org.test.root.service.CategoryCache;
import org.test.root.service.ManagingService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CategoryController {


    @Qualifier("persistent_category")

    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private ManagingService managingService;

    @GetMapping("categories/all")
    public List<Category> getAllCategories() {
        return categoryCache.getAllCategories();
    }

    @PostMapping("categories/{name}")
    public Category createCategory(@PathVariable String name) {
        Category category = managingService.createCategory(name);
        categoryCache.saveCategory(category);
        return category;
    }

    @DeleteMapping(value = "/categories/{id}")
    public Category deleteCategory(@PathVariable Long id) {
        Category category = categoryCache.getCategoryById(id);
        categoryCache.deleteCategoryById(id);
        return category;
    }
}
