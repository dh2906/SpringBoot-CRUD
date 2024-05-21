package com.example.demo.repository;

import com.example.demo.dto.AddArticleDto;
import com.example.demo.dto.UpdateArticleDto;
import com.example.demo.entity.Article;
import java.util.HashMap;

public class Articles {
    private final HashMap<Integer, Article> articleList = new HashMap<>();
    private static Integer id = 0;

    public void append(AddArticleDto body) {
        articleList.put(++id, new Article(body));
    }

    public Article value(Integer id) {
        return articleList.get(id);
    }

    public static Integer getId() {
        return id;
    }

    public static void decreaseId() {
        id--;
    }

    public HashMap<Integer, Article> getAll() {
        return articleList;
    }

    public void edit(Integer id, UpdateArticleDto body) {
        articleList.put(id, new Article(body, articleList.get(id).getPostDate()));
    }

    public void edit(Integer id, Article body) {
        articleList.put(id, body);
    }

    public boolean checkContainId(Integer id) {
        return articleList.containsKey(id);
    }

    public void delete(Integer id) {
        articleList.remove(id);
    }
}
