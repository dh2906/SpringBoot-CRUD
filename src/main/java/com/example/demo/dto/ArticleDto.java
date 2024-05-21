package com.example.demo.dto;

import com.example.demo.validate.DtoValidater;

public class ArticleDto {
    protected String name;
    protected String title;
    protected String detail;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public boolean valigate(ArticleDto body) {
        return DtoValidater.valigate(body);
    }
}
