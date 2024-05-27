package com.example.demo.dto.request;

import com.example.demo.validate.DtoValidater;
import lombok.Getter;

@Getter
public class RequestArticleDto {
    protected Integer board_id;
    protected String title;
    protected String content;

    public boolean validate(RequestArticleDto body) {
        return DtoValidater.validate(body);
    }
}
