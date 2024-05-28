package com.example.demo.repository;

import com.example.demo.dto.request.AddRequestArticleDto;
import com.example.demo.dto.request.UpdateRequestArticleDto;
import com.example.demo.dto.response.ViewResponseDto;
import com.example.demo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.View;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryJdbc implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public void append(AddRequestArticleDto dto) {
        String sql = "INSERT INTO article (author_id, board_id, title, content, created_date, modified_date)" +
                    "VALUES (?, ?, ?, ?, now(), now())";
        jdbcTemplate.update(sql, dto.getAuthor_id(), dto.getBoard_id(), dto.getTitle(), dto.getContent());
    }

    @Override
    public Optional<Article> findById(Integer id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        try {
            Article article = jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> {
                        Article temp = new Article(
                                rs.getInt("id"),
                                rs.getInt("author_id"),
                                rs.getInt("board_id"),
                                rs.getString("title"),
                                rs.getString("content"),
                                rs.getTimestamp("created_date").toLocalDateTime(),
                                rs.getTimestamp("modified_date").toLocalDateTime()
                        );
                        return temp;
                    }, id);
            return Optional.of(article);
        }

        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Article getRecent() {
        String sql = "SELECT * FROM article ORDER BY id DESC LIMIT 1";
        Article article = jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> {
                    Article temp = new Article(
                            rs.getInt("id"),
                            rs.getInt("author_id"),
                            rs.getInt("board_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getTimestamp("created_date").toLocalDateTime(),
                            rs.getTimestamp("modified_date").toLocalDateTime()
                    );
                    return temp;
                });
        return article;
    }

    @Transactional
    @Override
    public void edit(Integer id, UpdateRequestArticleDto body) {
        String sql = "UPDATE article SET title=?, content=?, modified_date=now() WHERE id = ?";
        jdbcTemplate.update(sql, body.getTitle(), body.getContent(), id);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM article WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Article> getArticles() {
        String sql = "SELECT * FROM article ORDER BY board_id, id";
        List<Article> articles = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Article temp = new Article(
                            rs.getInt("id"),
                            rs.getInt("author_id"),
                            rs.getInt("board_id"),
                            rs.getString(   "title"),
                            rs.getString("content"),
                            rs.getTimestamp("created_date").toLocalDateTime(),
                            rs.getTimestamp("modified_date").toLocalDateTime()
                    );
                    return temp;
                });
        return articles;
    }

    public boolean isEmpty() {
        String sql = "SELECT count(*) FROM article";
        int cnt = jdbcTemplate.queryForObject(sql, Integer.class);

        if (cnt == 0)
            return true;

        return false;
    }

    public boolean containId(Integer id) {
        String sql = "SELECT count(*) FROM article WHERE id = ?";
        int cnt = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return cnt > 0;
    }

    public boolean containBoardId(Integer boardId) {
        String sql = "SELECT count(*) FROM article WHERE board_id = ?";
        int cnt = jdbcTemplate.queryForObject(sql, Integer.class, boardId);

        return cnt > 0;
    }

    public List<Article> getArticlesByBoardId(Integer boardId) {
        String sql = "SELECT * FROM article WHERE board_id = ?";
        List<Article> articles = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Article temp = new Article(
                            rs.getInt("id"),
                            rs.getInt("author_id"),
                            rs.getInt("board_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getTimestamp("created_date").toLocalDateTime(),
                            rs.getTimestamp("modified_date").toLocalDateTime()
                    );
                    return temp;
                }, boardId);
        return articles;
    }

    public List<ViewResponseDto> getArticlesViewByBoardId(Integer boardId) {
        String sql = "SELECT * FROM article JOIN member ON article.author_Id = member.id         WHERE board_id = ?";
        List<ViewResponseDto> response = jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    ViewResponseDto temp = new ViewResponseDto(
                            rs.getString("title"),
                            rs.getString("name"),
                            rs.getTimestamp("created_date").toLocalDateTime(),
                            rs.getString("content")
                    );
                    return temp;
                }, boardId);
        return response;
    }
}
