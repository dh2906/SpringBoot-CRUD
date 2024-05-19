package com.example.demo.dto;

import java.util.Date;

public class AddArticleDto extends ArticleDto{
    private Date postDate = new Date();

    public Date getPostDate() {
        return postDate;
    }
}
