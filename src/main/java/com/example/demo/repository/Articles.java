package com.example.demo.repository;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;

import java.util.HashMap;

public class Articles {
    private final HashMap<Integer, Article> articleList = new HashMap<>();
    private static Integer id = 1;

    public void append(ArticleDto body) {
        articleList.put(id, new Article(body));
        id++;
    }

    public Article value(Integer id) {
        return articleList.get(id);
    }

    public static Integer getId() {
        return id;
    }

    public void edit(Integer id, ArticleDto body) {
        articleList.put(id, new Article(body));
    }

    public boolean checkContainId(Integer id) {
        return articleList.containsKey(id);
    }

    public void delete(Integer id) {
        articleList.remove(id);
    }
}
