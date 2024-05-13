package com.example.demo.unit;

import com.example.demo.dto.AddArticleDto;
import java.util.HashMap;

public class Articles {
    private final HashMap<Integer, Article> articleList = new HashMap<>();
    private static Integer id = 1;

    public void appendArticle(AddArticleDto body) {
        articleList.put(id, new Article(body));
        id++;
    }

    public Article index(Integer id) {
        return articleList.get(id);
    }

    public static Integer getId() {
        return id;
    }

    public void edit(Integer id, AddArticleDto body) {
        articleList.put(id, new Article(body));
    }

    public boolean checkContainId(Integer id) {
        return articleList.containsKey(id);
    }

    public void delete(Integer id) {
        articleList.remove(id);
    }
}
