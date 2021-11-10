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

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Author> getAuthorByName(String name) {
        if (name == null) {
            throw new AuthorIsNullException("Name is null");
        }
        List<Author> authors = authorRepository.findByName(name);
        if (authors.isEmpty()) {
            throw new AuthorNotFoundException("No Author with name " + name);
        }
        return authors;
    }

    @Transactional(readOnly = true)
    public Author getAuthorById(Long id) {
        checkAuthorId(id);
        return checkAuthorById(id);
    }

    @Transactional(readOnly = true)
    public List<Author> getAuthorsByBookId(Long bookId) {
        return authorRepository.findAuthorsByBookId(bookId);
    }

    public Author createAuthor(Author author) {
        checkAuthor(author);
        return authorRepository.save(author);
    }

    public Author updateAuthor(Author author, Long id) {
        checkAuthor(author);
        checkAuthorId(id);
        checkAuthorById(id);
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        checkAuthorId(id);
        checkAuthorById(id);
        authorRepository.deleteById(id);
    }


    private void checkAuthor(Author author) {
        if (author == null) {
            throw new AuthorIsNullException("Author Is null");
        }
    }

    private void checkAuthorId(Long authorId) {
        if (authorId == null) {
            throw new AuthorIsNullException("Id is null");
        }
    }

    private Author checkAuthorById(Long id) {
        Optional<Author> optAuthor = authorRepository.findById(id);
        if (optAuthor.isEmpty()) {
            throw new AuthorNotFoundException("No Author with id " + id);
        }
        return optAuthor.get();
    }
}
