package org.test.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.root.model.Category;
import org.test.root.model.News;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ManagingService {

    @Autowired
    private CategoryCache categoryCache;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    public News createNews(String nameTopic, String content, String publicationDate, Category category) {
        News news = new News();
        news.setNameTopic(nameTopic);
        news.setContent(content);
        news.setCategory(category);
        news.setPublicationDate(LocalDate.parse(publicationDate, formatter).toString());
        return news;
    }
}