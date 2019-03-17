package org.test.root.service;

import org.test.root.model.Category;
import org.test.root.model.News;

import java.util.List;

public interface NewsCache {
    void saveNews(News news);

    void deleteNewsById(Long id);

    List<News> getAllNews();

    News getNewsById(Long id);

    List<News> findByNameTopicIgnoreCaseContaining(String topic);

    List<News> findByCategory(Category category);

    List<News> findByContent(String content);

}
