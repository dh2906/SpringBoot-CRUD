package com.example.demo.controller;

import com.example.demo.dto.request.AddRequestArticleDto;
import com.example.demo.dto.request.UpdateRequestArticleDto;
import com.example.demo.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping()
public class ArticleController {
    Service service;

    public ArticleController(Service service) {
        this.service = service;
    }

    @GetMapping("/articles")
    public ResponseEntity getAllArticle(@RequestParam(
            value = "boardId", required = false) Integer boardId) {
        if (boardId == null) {
            if (service.checkIsEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.ok(service.getAllArticle());
        }

        if (!service.checkContainBoardId(boardId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(service.getBoardAllArticle(boardId));
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity getArticle(@PathVariable Integer id) {
        if (!service.checkContainId(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(service.findArticle(id));
    }

    @PostMapping("/articles")
    public ResponseEntity postArticle(@RequestBody AddRequestArticleDto request){
        if (!service.appendArticle(request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.created(URI.create("/article")).build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity putArticle(@RequestBody UpdateRequestArticleDto request, @PathVariable Integer id) {
        if (!service.editArticle(id, request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.findArticle(id));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity deleteArticle(@PathVariable Integer id) {
        if (!service.deleteArticle(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.noContent().build();
    }
}
