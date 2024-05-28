package com.example.demo.service;

import com.example.demo.dto.request.AddRequestArticleDto;
import com.example.demo.dto.request.UpdateRequestArticleDto;
import com.example.demo.dto.response.ViewResponseDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.validate.DtoValidater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                          BoardRepository boardRepository,
                          MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public boolean validateRequestBody(AddRequestArticleDto body) {
        return DtoValidater.validate(body);
    }

    public Optional<Article> getArticleById(Integer id) {
        return articleRepository.findById(id);
    }

    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

    public List<Article> getArticlesByBoardId(Integer boardId) {
        return articleRepository.getArticlesByBoardId(boardId);
    }

    public List<Article> checkGetArticlesByBoardId(Integer boardId) {
        if (boardId == null)
            return getArticles();

        return getArticlesByBoardId(boardId);
    }

    public List<ViewResponseDto> getArticlesView() {
        return boardRepository.findAllToView();
    }


    @Transactional
    public boolean appendArticle(AddRequestArticleDto body) {
        if (!validateRequestBody(body))
            return false;

        articleRepository.append(body);
        return true;
    }

    public boolean checkIsEmpty() {
        return articleRepository.isEmpty();
    }

    public boolean checkContainId(Integer id) {
        return articleRepository.containId(id);
    }

    public boolean checkContainBoardId(Integer boardId) {
        return articleRepository.containBoardId(boardId);
    }

    @Transactional
    public boolean editArticle(Integer id, UpdateRequestArticleDto body) {
        if (!articleRepository.containId(id))
            return false;

        articleRepository.edit(id, body);
        return true;
    }

    @Transactional
    public boolean deleteArticle(Integer id) {
        if (!articleRepository.containId(id))
            return false;

        articleRepository.delete(id);
        return true;
    }


    public List<ViewResponseDto> getArticlesViewByBoardId(Integer boardId) {
        return articleRepository.getArticlesViewByBoardId(boardId);
    }

    public String getBoardName(Integer boardId) {
        return boardRepository.findById(boardId);
    }

    public Article getRecentArticle() {
        return articleRepository.getRecent();
    }

    public String getAuthorName(Integer authorId) {
        return memberRepository.findName(authorId);
    }
}
