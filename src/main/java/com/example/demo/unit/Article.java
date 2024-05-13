package com.example.demo.unit;

import com.example.demo.dto.AddArticleDto;

import java.util.Date;

public class Article {
    public String name;
    public String title;
    public String detail;
    public Date postDate;

    Article(AddArticleDto body) {
        this.name = body.getName();
        this.title = body.getTitle();
        this.detail = body.getDetail();
        this.postDate = new Date();
    }
}
