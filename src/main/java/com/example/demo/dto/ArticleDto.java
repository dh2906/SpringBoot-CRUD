package com.example.demo.dto;

import java.util.Date;

public class ArticleDto {
    private String name;
    private String title;
    private String detail;
    private Date postDate;
    private Date editDate;

    ArticleDto() {
        postDate = new Date();
        editDate = new Date();
    }

    public String getName() {
        return name;
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
}
