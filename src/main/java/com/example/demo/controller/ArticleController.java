package com.example.demo.controller;

import com.example.demo.dto.request.AddRequestArticleDto;
import com.example.demo.dto.request.UpdateRequestArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping()
public class ArticleController {
    ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public ResponseEntity getArticlesByBoardId(@RequestParam(
            value = "boardId", required = false) Integer boardId) {
        return ResponseEntity.ok(
                articleService.checkGetArticlesByBoardId(boardId));
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity getArticle(@PathVariable Integer id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping("/articles")
    public ResponseEntity postArticle(@RequestBody AddRequestArticleDto request){
        if (!articleService.appendArticle(request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.created(URI.create("/article"))
                .body(articleService.getRecentArticle());
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity putArticle(@RequestBody UpdateRequestArticleDto request, @PathVariable Integer id) {
        if (!articleService.editArticle(id, request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleService.getArticleById(id));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity deleteArticle(@PathVariable Integer id) {
        if (!articleService.deleteArticle(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.noContent().build();
    }
}
