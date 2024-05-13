package com.example.demo.valigate;

import com.example.demo.dto.AddArticleDto;

public class DtoValigater {
    public static boolean valigate(AddArticleDto dto) {
        if (dto.getName() == null || dto.getName().isBlank() ||
            dto.getTitle() == null || dto.getTitle().isBlank() ||
            dto.getDetail() == null || dto.getDetail().isBlank())
            return false;

        return true;
    }
}
