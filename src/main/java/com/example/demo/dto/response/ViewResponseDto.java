package com.example.demo.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ViewResponseDto {
    private String title;
    private String authorName;
    private String createdDate;
    private String content;

    public ViewResponseDto(String title, String authorName, LocalDateTime createdDate, String content) {
        this.title = title;
        this.authorName = authorName;
        this.createdDate = getDateFormat(createdDate);
        this.content = content;
    }

    public String getDateFormat(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss");
        return date.format(formatter);
    }
}
