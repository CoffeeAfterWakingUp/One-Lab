package com.example.practice1.repository.impl;

import com.example.practice1.entity.Review;
import com.example.practice1.repository.ReviewRepository;
import com.example.practice1.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
    private static final String SELECT_BY_REVIEWER_NAME = "SELECT * FROM review WHERE reviewerName=?";
    private static final String SELECT_BY_BOOK_TITLE = "" +
            "SELECT r.id, r.reviewerName, r.rating, r.book FROM review r " +
            "INNER JOIN book b " +
            "ON r.book=b.id " +
            "WHERE b.title=?";
    private static final String SELECT_BY_AUTHOR_NAME = "" +
            "SELECT r.id, r.reviewerName, r.rating, r.book FROM review r " +
            "INNER JOIN book_author ba " +
            "ON r.book=ba.book " +
            "INNER JOIN author a " +
            "ON ba.author=a.id " +
            "WHERE a.name=?";

    private JdbcTemplate jdbcTemplate;
    private Service service;

    @Autowired
    public void setService(Service service) {
        this.service = service;
    }

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Review> findByReviewerName(String reviewerName) {
        return jdbcTemplate.query(SELECT_BY_REVIEWER_NAME, this::mapRowToReview, reviewerName);
    }

    @Override
    public List<Review> findByBookTitle(String title) {
        return jdbcTemplate.query(SELECT_BY_BOOK_TITLE, this::mapRowToReview, title);
    }

    @Override
    public List<Review> findByAuthorName(String authorName) {
        return jdbcTemplate.query(SELECT_BY_AUTHOR_NAME, this::mapRowToReview, authorName);
    }

    private Review mapRowToReview(ResultSet rs, int rowNum) throws SQLException {
        return new Review(
                rs.getLong("id"),
                rs.getString("reviewerName"),
                rs.getInt("rating"),
                service.getAllBooks().get((int) rs.getLong("book"))
        );
    }


}
