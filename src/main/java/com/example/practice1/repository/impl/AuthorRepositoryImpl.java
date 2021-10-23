package com.example.practice1.repository.impl;

import com.example.practice1.entity.Author;
import com.example.practice1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    private static final String SELECT_ALL = "SELECT * FROM author";
    private static final String SELECT_BY_NAME = "SELECT * FROM author WHERE name=?";
    private static final String SELECT_BY_ID = "SELECT * FROM author WHERE id=?";
    private static final String SELECT_BY_BOOK_ID = "" +
            "SELECT a.id, a.name, a.surname FROM author a " +
            "JOIN book_author b " +
            "ON a.id=b.author " +
            "WHERE b.book=?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::mapRowToAuthor);
    }

    @Override
    public List<Author> findByName(String name) {
        return jdbcTemplate.query(SELECT_BY_NAME, this::mapRowToAuthor, name);
    }

    @Override
    public Author findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, this::mapRowToAuthor, id);
    }

    @Override
    public List<Author> findAuthorsByBookId(Long id) {
        return jdbcTemplate.query(SELECT_BY_BOOK_ID, this::mapRowToAuthor, id);
    }

    private Author mapRowToAuthor(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        //List<Book> books = service.getBooksByAuthorId(id);
        return new Author(
                id,
                rs.getString("name"),
                rs.getString("surname")
        );
    }
}
