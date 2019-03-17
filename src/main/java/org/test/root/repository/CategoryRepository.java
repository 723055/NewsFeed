package org.test.root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.root.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryById(Long id);
}
