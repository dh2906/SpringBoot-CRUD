package com.example.demo.repository;

import com.example.demo.dto.request.AddRequestArticleDto;
import com.example.demo.dto.request.UpdateRequestArticleDto;
import com.example.demo.dto.response.ResponseArticleDto;
import com.example.demo.dto.response.ViewResponseDto;
import com.example.demo.entity.Article;

import java.util.List;

public interface ArticleRepository {
    public void append(AddRequestArticleDto body);
    public Article findById(Integer id);
    public void edit(Integer id, UpdateRequestArticleDto body);
    public void delete(Integer id);
    public List<Article> getAll();
    public List<Article> getBoardAll(Integer boardId);
    public List<ViewResponseDto> getBoardAllToView(Integer boardId);
    public Article getRecent();
    public boolean isEmpty();
    public boolean containId(Integer id);
    public boolean containBoardId(Integer boardId);
}
