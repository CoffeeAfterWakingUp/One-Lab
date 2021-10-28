package com.example.practice1.service;

import com.example.practice1.entity.Author;
import com.example.practice1.exception.AuthorIsNullException;
import com.example.practice1.exception.AuthorNotFoundException;
import com.example.practice1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    public List<Author> getAuthorByName(String name) {
        if (name == null) {
            throw new AuthorIsNullException("Name is null");
        }
        List<Author> authors = authorRepository.findByName(name);
        if (authors.isEmpty()) {
            throw new AuthorNotFoundException("Author with name " + name + " not found");
        }
        return authors;
    }

    public Author getAuthorById(Long id) {
        if (id == null) {
            throw new AuthorIsNullException("Id is null");
        }
        Optional<Author> optAuthor = authorRepository.findById(id);
        if (optAuthor.isEmpty()) {
            throw new AuthorNotFoundException("Author with id " + id + " not found");
        }
        return optAuthor.get();
    }


    public List<Author> getAuthorsByBookId(Long bookId) {
        return authorRepository.findAuthorsByBookId(bookId);
    }

}
