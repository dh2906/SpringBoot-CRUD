package com.example.demo.entity;

import com.example.demo.dto.ArticleDto;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private Member member = new Member();
    private String title;
    private String detail;
    private Date postDate;
    private Date editDate;

    public Article(ArticleDto body) {
        this.member.setName(body.getName());
        this.title = body.getTitle();
        this.detail = body.getDetail();
        this.postDate = body.getPostDate();
    }

    public String getName() {
        return member.getName();
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getDateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:MM");
        return simpleDateFormat.format(date);
    }
}
