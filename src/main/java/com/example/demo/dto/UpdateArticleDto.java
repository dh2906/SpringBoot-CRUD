package com.example.demo.dto;

import java.util.Date;

public class UpdateArticleDto extends ArticleDto{
    private Date editDate = new Date();

    public Date getEditDate() {
        return editDate;
    }
}
