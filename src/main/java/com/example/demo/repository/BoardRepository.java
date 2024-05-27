package com.example.demo.repository;

import com.example.demo.dto.response.ViewResponseDto;
import com.example.demo.entity.Board;

import java.util.List;

public interface BoardRepository {
    public String findById(Integer id);
    public void delete(Integer id);
    public List<Board> findAll();
    public List<ViewResponseDto> findAllToView();
}
