package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.Articles;
import com.example.demo.valigate.DtoValigater;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class ArticleService {
    private final Articles articleList = new Articles();

    public boolean valigateRequestBody(ArticleDto body) {
        return DtoValigater.valigate(body);
    }

    public Article findValue(Integer id) {
        return articleList.value(id);
    }

    public ResponseEntity getArticle(Integer id) {
        if (!articleList.checkContainId(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(findValue(id));
    }

    public ResponseEntity appendArticle(ArticleDto body) {
        if (!valigateRequestBody(body))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleList.append(body);
        return ResponseEntity.created(URI.create("/article")).build();
    }

    public ResponseEntity editArticle(Integer id, ArticleDto body) {
        if (!articleList.checkContainId(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleList.edit(id, body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity deleteArticle(Integer id) {
        if (!articleList.checkContainId(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        articleList.delete(id);
        return ResponseEntity.noContent().build();
    }
}
