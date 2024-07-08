package com.redmath.lec2.Data.base.connection.News;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/allnews")
    public ResponseEntity<List<News>> get(){
       return ResponseEntity.ok(newsRepository.findAll());
    }

    @PostMapping("/news")
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News savedNews = newsRepository.save(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNews);
    }
//@GetMapping("/allnews")
//public ResponseEntity<String> get() {
//    return ResponseEntity.ok("{\"message\": \"Hello, World!\"}");
//    // return ResponseEntity.ok(newsRepository.findAll());
//}
}
