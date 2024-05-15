package com.example.demo.entity;

import com.example.demo.dto.ArticleDto;

import java.util.Date;

public class Article {
    public String name;
    public String title;
    public String detail;
    public Date postDate;

    public Article(ArticleDto body) {
        this.name = body.getName();
        this.title = body.getTitle();
        this.detail = body.getDetail();
        this.postDate = new Date();
    }
}
