package com.example.practice1.repository;

import com.example.practice1.entity.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    List<Book> findByTitle(String title);

    List<Book> findBooksByAuthorName(String authorName);

    List<Book> findBooksByAuthorId(Long id);
}
