package com.example.practice1.repository.impl;

import com.example.practice1.database.Author;
import com.example.practice1.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public List<Author> findAll() {
        return Author.getAuthors();
    }

    @Override
    public List<Author> findByName(String name) {
        return Author.getAuthors().stream()
                .filter(author -> author.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Author findById(Long id) {
        return Author.getAuthors().stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
