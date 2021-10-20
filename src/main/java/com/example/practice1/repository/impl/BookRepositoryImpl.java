package com.example.practice1.repository.impl;

import com.example.practice1.database.Book;
import com.example.practice1.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Override
    public List<Book> findAll() {
        return Book.getBooks();
    }

    @Override
    public Book findByTitle(String title) {
        return Book.getBooks().stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        return Book.getBooks().stream()
                .filter(book -> book.getAuthor().getName().equals(authorName))
                .collect(Collectors.toList());
    }
}
