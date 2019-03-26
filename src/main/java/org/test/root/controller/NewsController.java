package org.test.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.test.root.model.Category;
import org.test.root.model.News;
import org.test.root.model.User;
import org.test.root.repository.UserRepository;
import org.test.root.service.CategoryCache;
import org.test.root.service.ManagingService;
import org.test.root.service.NewsCache;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/")
public class NewsController {

    @Qualifier("persistent_news")

    @Autowired
    private NewsCache newsCache;

    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private ManagingService managingService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("news/{nameTopic}/{content}/{category}")
    public News createNews(@PathVariable String nameTopic, @PathVariable String content, @PathVariable Category category) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = LocalDateTime.now().format(formatter);

        News news = managingService.createNews(nameTopic, content, category, date);
        newsCache.saveNews(news);
        return news;
    }

    @DeleteMapping(value = "/news/{id}")
    public News deleteNews(@PathVariable Long id) {
        News deletedNews = newsCache.getNewsById(id);
        newsCache.deleteNewsById(id);
        return deletedNews;
    }

    @GetMapping("news/newsList")
    public List<News> newsList() {
        return newsCache.getAllNews();
    }

    @GetMapping("news/new/{id}/{nameTopic}/{content}")
    public News changeNews(@PathVariable Long id, @PathVariable String nameTopic, @PathVariable String content,
                            String publicationDate) {
        News news = newsCache.getNewsById(id);
        news.setNameTopic(nameTopic);
        news.setContent(content);
        news.setPublicationDate(publicationDate);
        newsCache.saveNews(news);
        return news;
    }

    @GetMapping("news/{nameTopic}")
    public List<News> findNewsByNameTopic(@PathVariable String nameTopic) {
        List<News> news = newsCache.findByNameTopicIgnoreCaseContaining(nameTopic);
        return news;
    }

    @GetMapping("news/{category}")
    public List<News> findNewsByCategory(@PathVariable Category category) {
        List<News> news = newsCache.findByCategory(category);
        return news;
    }

    @GetMapping("news/{content}")
    public List<News> findNewsByNameContent(@PathVariable String content) {
        List<News> news = newsCache.findByContent(content);
        return news;
    }

    @PostMapping("news/create")
    public String createTestData() {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setName("Category-" + i);
            categoryCache.saveCategory(category);
            categories.add(category);


            Random random = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            for (int k = 0; k < 5; k++) {
                News news = new News();
                news.setNameTopic("TOPIC " + k);
                news.setContent("Some text \n" + random.nextInt(100000000) + "\n"
                        + "more some text" + random.nextInt(100000));
                String date = LocalDateTime.now().format(formatter);
                news.setPublicationDate(date);
                news.setCategory(categories.get(i));

                newsCache.saveNews(news);
            }
        }
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        userRepository.save(user);

        return "Saved!";
    }
}
