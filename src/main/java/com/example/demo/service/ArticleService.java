package com.example.demo.service;

import com.example.demo.dto.AddArticleDto;
import com.example.demo.dto.UpdateArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.Articles;
import com.example.demo.valigate.DtoValigater;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ArticleService {
    private final Articles articleList = new Articles();

    public boolean valigateRequestBody(AddArticleDto body) {
        return DtoValigater.valigate(body);
    }

    public Article findValue(Integer id) {
        return articleList.value(id);
    }

    public HashMap<Integer, Article> getAllArticle() {
        return articleList.getAll();
    }

    public Article getArticle(Integer id) {
        return findValue(id);
    }

    public boolean checkContainId(Integer id) {
        return articleList.checkContainId(id);
    }

    public boolean appendArticle(AddArticleDto body) {
        if (!valigateRequestBody(body))
            return false;

        articleList.append(body);
        return true;
    }

    public boolean editArticle(Integer id, UpdateArticleDto body) {
        if (!articleList.checkContainId(id))
            return false;

        articleList.edit(id, body);
        return true;
    }

    public boolean deleteArticle(Integer id) {
        final int CUR_ID = Articles.getId();

        if (!articleList.checkContainId(id))
            return false;

        articleList.delete(id);

        if (CUR_ID > 2) {
            for (int i = id; i < CUR_ID; i++)
                articleList.edit(i, findValue(i + 1));

            articleList.delete(CUR_ID - 1);
        }

        Articles.decreaseId();

        return true;
    }
}
