package com.example.practice1.service;

import com.example.practice1.database.Author;
import com.example.practice1.database.Book;
import com.example.practice1.database.Review;
import com.example.practice1.exception.BookTitleIsNullException;
import com.example.practice1.repository.AuthorRepository;
import com.example.practice1.repository.BookRepository;
import com.example.practice1.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public Service(AuthorRepository authorRepository, BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title) {
        if (title == null) {
            throw new BookTitleIsNullException();
        }
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        if (authorName == null) {
            System.out.println("");
        }
        return bookRepository.findBooksByAuthorName(authorName);
    }

    public List<Review> getReviewsByReviewerName(String reviewerName) {
        if (reviewerName == null) {

        }
        return reviewRepository.findByReviewerName(reviewerName);
    }

    public List<Review> getReviewsByBookTitle(String bookTitle) {
        if (bookTitle == null) {

        }
        return reviewRepository.findByBookTitle(bookTitle);
    }

    public List<Review> getReviewsByAuthorName(String authorName) {
        if (authorName == null) {

        }
        return reviewRepository.findByAuthorName(authorName);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorByName(String name) {
        if (name == null) {

        }
        return authorRepository.findByName(name);
    }

    public Author getAuthorById(Long id) {
        if (id == null) {

        }
        return authorRepository.findById(id);
    }
}
