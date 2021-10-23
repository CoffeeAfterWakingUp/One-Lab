package com.example.practice1.repository.impl;

import com.example.practice1.entity.Author;
import com.example.practice1.entity.Book;
import com.example.practice1.repository.BookRepository;
import com.example.practice1.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private static final String SELECT_ALL = "SELECT * FROM book";
    private static final String SELECT_BY_TITLE = "SELECT * FROM book WHERE title=?";
    private static final String SELECT_BY_AUTHORS_NAME =
            "SELECT b.id, b.title, b.genre FROM book b " +
                    "JOIN book_author ba " +
                    "ON b.id=ba.book " +
                    "JOIN author a " +
                    "ON a.id=ba.author " +
                    "WHERE a.name=?";
    private static final String SELECT_BY_AUTHOR_ID =
            "SELECT b.id, b.title, b.genre FROM book b " +
                    "JOIN book_author ba " +
                    "ON b.id=ba.book " +
                    "WHERE ba.author=?";

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
    public List<Book> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::mapRowToBook);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return jdbcTemplate.query(SELECT_BY_TITLE, this::mapRowToBook, title);
    }

    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        return jdbcTemplate.query(SELECT_BY_AUTHORS_NAME, this::mapRowToBook, authorName);
    }

    @Override
    public List<Book> findBooksByAuthorId(Long id) {
        return jdbcTemplate.query(SELECT_BY_AUTHOR_ID, this::mapRowToBook, id);
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        List<Author> authors = service.getAuthorsByBookId(id);
        return new Book(
                id,
                rs.getString("title"),
                Book.Genre.valueOf(rs.getString("genre")),
                authors
        );
    }
}
