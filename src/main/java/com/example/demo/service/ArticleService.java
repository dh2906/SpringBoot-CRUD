package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.Articles;
import com.example.demo.valigate.DtoValigater;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final Articles articleList = new Articles();

    public boolean valigateRequestBody(ArticleDto body) {
        return DtoValigater.valigate(body);
    }

    public boolean checkContainId(Integer id) {
        return articleList.checkContainId(id);
    }

    public Article findValue(Integer id) {
        return articleList.value(id);
    }

    public void appendArticle(ArticleDto body) {
        articleList.append(body);
    }

    public void editContent(Integer id, ArticleDto body) {
        articleList.edit(id, body);
    }

    public void deleteArticle(Integer id) {
        articleList.delete(id);
    }
}
