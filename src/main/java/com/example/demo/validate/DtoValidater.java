package com.example.demo.validate;

import com.example.demo.dto.request.RequestArticleDto;

public class DtoValidater {
    public static boolean validate(RequestArticleDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank() ||
            dto.getContent() == null || dto.getContent().isBlank())
            return false;

        return true;
    }
}
