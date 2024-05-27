package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryJdbc implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findName(Integer id) {
        String sql = "SELECT name FROM member WHERE id = ?";
        String name = jdbcTemplate.queryForObject(sql, String.class, id);

        return name;
    }
}
