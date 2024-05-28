package com.example.demo.repository;

import com.example.demo.dto.request.AddRequestArticleDto;
import com.example.demo.dto.request.UpdateRequestArticleDto;
import com.example.demo.dto.response.ResponseArticleDto;
import com.example.demo.dto.response.ViewResponseDto;
import com.example.demo.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    public void append(AddRequestArticleDto body);
    public Optional<Article> findById(Integer id);
    public void edit(Integer id, UpdateRequestArticleDto body);
    public void delete(Integer id);
    public List<Article> getArticles();
    public List<Article> getArticlesByBoardId(Integer boardId);
    public List<ViewResponseDto> getArticlesViewByBoardId(Integer boardId);
    public Article getRecent();
    public boolean isEmpty();
    public boolean containId(Integer id);
    public boolean containBoardId(Integer boardId);
}
