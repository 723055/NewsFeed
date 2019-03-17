package org.test.root.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.root.model.Category;
import org.test.root.model.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    News getNewsById(Long id);

    List<News> findByNameTopicIgnoreCaseContaining(String topic);

    List<News> findByCategory(Category category);

    List<News> findByContent(String content);

}
