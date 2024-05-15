package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/article")
public class ArticleController {
    ArticleService articleService = new ArticleService();

    @PostMapping()
    public ResponseEntity post(@RequestBody ArticleDto request){
        return articleService.appendArticle(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        return articleService.getArticle(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@RequestBody ArticleDto request, @PathVariable Integer id) {
        return articleService.editArticle(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        return articleService.deleteArticle(id);
    }
}
