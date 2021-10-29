package com.example.practice1.service;

import com.example.practice1.entity.Book;
import com.example.practice1.exception.AuthorIsNullException;
import com.example.practice1.exception.BookIsNullException;
import com.example.practice1.exception.BookNotFoundException;
import com.example.practice1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByTitle(String title) {
        if (title == null) {
            throw new BookIsNullException("Book Title is null");
        }
        List<Book> books = bookRepository.findByTitle(title);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Book with title " + title + " not found");
        }
        return books;
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthorName(String authorName) {
        if (authorName == null) {
            throw new BookIsNullException("Author Name is null");
        }
        List<Book> books = bookRepository.findBooksByAuthorName(authorName);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Books with author " + authorName + " not found");
        }
        return books;
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthorId(Long authorId) {
        if (authorId == null) {
            throw new AuthorIsNullException("Id is null");
        }
        return bookRepository.findBooksByAuthorId(authorId);
    }
}
