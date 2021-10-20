package com.example.practice1.repository;

import com.example.practice1.database.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findByTitle(String title);

    List<Book> findBooksByAuthorName(String authorName);
}
