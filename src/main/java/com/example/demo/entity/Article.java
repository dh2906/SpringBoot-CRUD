package com.example.demo.entity;

import com.example.demo.dto.AddArticleDto;
import com.example.demo.dto.UpdateArticleDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private Member member = new Member();
    private String title;
    private String detail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private Date postDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private Date editDate;

    public Article(AddArticleDto body) {
        this.member.setName(body.getName());
        this.title = body.getTitle();
        this.detail = body.getDetail();
        this.postDate = body.getPostDate();
        this.editDate = new Date();
    }

    public Article(UpdateArticleDto body, Date postDate) {
        this.member.setName(body.getName());
        this.title = body.getTitle();
        this.detail = body.getDetail();
        this.postDate = postDate;
        this.editDate = body.getEditDate();
    }

    public Article(Article body) {
        this.member.setName(body.getName());
        this.title = body.getTitle();
        this.detail = body.getDetail();
        this.postDate = body.postDate;
        this.editDate = body.editDate;
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

    public Date getPostDate() {
        return postDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public String getDateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
        return simpleDateFormat.format(date);
    }
}
