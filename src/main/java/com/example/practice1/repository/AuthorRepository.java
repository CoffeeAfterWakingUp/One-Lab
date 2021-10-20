package com.example.practice1.repository;


import com.example.practice1.database.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> findAll();

    List<Author> findByName(String name);

    Author findById(Long id);
}
