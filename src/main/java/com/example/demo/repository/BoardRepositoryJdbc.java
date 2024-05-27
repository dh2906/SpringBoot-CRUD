package com.example.demo.repository;

import com.example.demo.dto.response.ViewResponseDto;
import com.example.demo.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BoardRepositoryJdbc implements BoardRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public String findById(Integer id) {
        String sql = "SELECT name FROM board WHERE id = ?";
        String boardName = jdbcTemplate.queryForObject(sql, String.class, id);

        return boardName;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Board> findAll() {
        return null;
    }

    @Override
    public List<ViewResponseDto> findAllToView() {
        String sql = "SELECT * FROM article JOIN member ON article.author_id = member.id";
        List<ViewResponseDto> response = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    ViewResponseDto temp = new ViewResponseDto(
                            rs.getString("title"),
                            rs.getString("name"),
                            rs.getTimestamp("created_date").toLocalDateTime(),
                            rs.getString("content")
                    );
                    return temp;
                });
        return response;
    }


}
