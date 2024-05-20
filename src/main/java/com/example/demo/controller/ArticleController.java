package com.example.demo.controller;

import com.example.demo.dto.AddArticleDto;
import com.example.demo.dto.UpdateArticleDto;
import com.example.demo.repository.Articles;
import com.example.demo.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.net.URI;

@RestController
@RequestMapping()
public class ArticleController {
    ArticleService articleService = new ArticleService();

    @GetMapping("/posts")
    public ModelAndView viewArticles(ModelAndView modelAndView) {
        modelAndView.setViewName("community");
        modelAndView.addObject("items", articleService.getAllArticle());
        return modelAndView;
    }

    @GetMapping("/articles")
    public ResponseEntity getAllArticle() {
        if (Articles.getId() == 1)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(articleService.getAllArticle());
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity getArticle(@PathVariable Integer id) {
        if (!articleService.checkContainId(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @PostMapping("/articles")
    public ResponseEntity postArticle(@RequestBody AddArticleDto request){
        if (!articleService.appendArticle(request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.created(URI.create("/article")).build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity putArticle(@RequestBody UpdateArticleDto request, @PathVariable Integer id) {
        if (!articleService.editArticle(id, request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity deleteArticle(@PathVariable Integer id) {
        if (!articleService.deleteArticle(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.noContent().build();
    }
}
