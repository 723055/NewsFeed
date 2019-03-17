package org.test.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.root.model.Category;
import org.test.root.repository.CategoryRepository;

import java.util.List;

@Service("persistent_category")
public class CategoryCacheImpl implements CategoryCache {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category c = categoryRepository.getCategoryById(id);
        categoryRepository.save(c);
        categoryRepository.delete(c);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }
}
