package com.example.demo.service;

import com.example.demo.dto.request.AddRequestArticleDto;
import com.example.demo.dto.request.UpdateRequestArticleDto;
import com.example.demo.dto.response.ViewResponseDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemberRepositoryJdbc;
import com.example.demo.validate.DtoValidater;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.View;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public Service(ArticleRepository articleRepository,
                   BoardRepository boardRepository,
                   MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public boolean validateRequestBody(AddRequestArticleDto body) {
        return DtoValidater.validate(body);
    }

    public Article findArticle(Integer id) {
        return articleRepository.findById(id);
    }

    public List<Article> getAllArticle() {
        return articleRepository.getAll();
    }

    public List<ViewResponseDto> getAllArticleToView() {
        return boardRepository.findAllToView();
    }


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

    public boolean editArticle(Integer id, UpdateRequestArticleDto body) {
        if (!articleRepository.containId(id))
            return false;

        articleRepository.edit(id, body);
        return true;
    }

    public boolean deleteArticle(Integer id) {
        if (!articleRepository.containId(id))
            return false;

        articleRepository.delete(id);
        return true;
    }

    public List<Article> getBoardAllArticle(Integer boardId) {
        return articleRepository.getBoardAll(boardId);
    }

    public List<ViewResponseDto> getBoardAllArticleToView(Integer boardId) {
        return articleRepository.getBoardAllToView(boardId);
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
