package org.test.root.service;

import org.test.root.model.Category;

import java.util.List;

public interface CategoryCache {
    void saveCategory(Category category);

    void deleteCategoryById(Long id);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

}
