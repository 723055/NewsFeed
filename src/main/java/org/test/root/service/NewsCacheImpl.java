package org.test.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.root.model.Category;
import org.test.root.model.News;
import org.test.root.repository.NewsRepository;

import java.util.List;

@Service("persistent_news")
public class NewsCacheImpl implements NewsCache {

    @Autowired
    NewsRepository newsRepository;

    @Override
    public void saveNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public void deleteNewsById(Long id) {
        News n = newsRepository.getNewsById(id);
        // n.getCategory().clear();
        newsRepository.save(n);
        newsRepository.delete(n);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(Long id) {
        News news = newsRepository.findById(id).get();
        return news;
    }

    @Override
    public List<News> findByNameTopicIgnoreCaseContaining(String topic) {
        List<News> news = newsRepository.findByNameTopicIgnoreCaseContaining(topic);
        return news;
    }

    @Override
    public List<News> findByCategory(Category category) {
        List<News> news = newsRepository.findByCategory(category);
        return news;
    }

    @Override
    public List<News> findByContent(String content) {
        List<News> news = newsRepository.findByContent(content);
        return news;
    }
}
