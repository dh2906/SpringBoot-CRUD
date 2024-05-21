package com.example.demo.validate;

import com.example.demo.dto.ArticleDto;

public class DtoValidater {
    public static boolean valigate(ArticleDto dto) {
        if (dto.getName() == null || dto.getName().isBlank() ||
            dto.getTitle() == null || dto.getTitle().isBlank() ||
            dto.getDetail() == null || dto.getDetail().isBlank())
            return false;

        return true;
    }
}
