package com.example.demo.controller;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/article")
public class ArticleController {
    ArticleService articleService = new ArticleService();

    @PostMapping()
    public ResponseEntity addArticle(@RequestBody ArticleDto request){
        if (!articleService.valigateRequestBody(request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleService.appendArticle(request);
        return ResponseEntity.created(URI.create("/article")).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity checkArticle(@PathVariable Integer id) {
        if (!articleService.checkContainId(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(articleService.findValue(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity editArticle(@RequestBody ArticleDto request, @PathVariable Integer id) {
        if (!articleService.checkContainId(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleService.editContent(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArticle(@PathVariable Integer id) {
        if (!articleService.checkContainId(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
