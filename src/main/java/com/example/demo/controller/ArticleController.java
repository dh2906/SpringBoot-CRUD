package com.example.demo.controller;

import com.example.demo.unit.Articles;
import com.example.demo.dto.AddArticleDto;
import com.example.demo.valigate.DtoValigater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private Articles articleList = new Articles();

    @PostMapping()
    public ResponseEntity addArticle(@RequestBody AddArticleDto request){
        if (!DtoValigater.valigate(request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleList.appendArticle(request);
        return ResponseEntity.created(URI.create("/article")).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity checkArticle(@PathVariable Integer id) {
        if (!articleList.checkContainId(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(articleList.index(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity editArticle(@RequestBody AddArticleDto request, @PathVariable Integer id) {
        if (!DtoValigater.valigate(request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleList.edit(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArticle(@PathVariable Integer id) {
        if (!articleList.checkContainId(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleList.delete(id);
        return ResponseEntity.noContent().build();
    }
}
