package com.example.practice1.service;

import com.example.practice1.database.Author;
import com.example.practice1.database.Book;
import com.example.practice1.database.Review;
import com.example.practice1.exception.*;
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
            throw new BookIsNullException("Book Title is null");
        }
        Book book = bookRepository.findByTitle(title);
        if(book == null) {
            throw new BookNotFoundException("Book with title " + title + " not found");
        }
        return book;
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        if (authorName == null) {
            throw new BookIsNullException("Author Name is null");
        }
        List<Book> books = bookRepository.findBooksByAuthorName(authorName);
        if(books.isEmpty()) {
            throw new BookNotFoundException("Books with author " + authorName + " not found");
        }
        return books;
    }

    public List<Review> getReviewsByReviewerName(String reviewerName) {
        if (reviewerName == null) {
            throw new ReviewIsNullException("Reviewer Name is null");
        }
        List<Review> reviews = reviewRepository.findByReviewerName(reviewerName);
        if(reviews.isEmpty()) {
            throw new ReviewNotFoundException("Review with reviewer name " + reviewerName + " not found");
        }
        return reviews;
    }

    public List<Review> getReviewsByBookTitle(String bookTitle) {
        if (bookTitle == null) {
            throw new ReviewIsNullException("Book title is null");
        }
        List<Review> reviews = reviewRepository.findByBookTitle(bookTitle);
        if(reviews.isEmpty()) {
            throw new ReviewNotFoundException("Review with book title " + bookTitle + " not found");
        }
        return reviews;
    }

    public List<Review> getReviewsByAuthorName(String authorName) {
        if (authorName == null) {
            throw new ReviewIsNullException("Author name is null");
        }
        List<Review> reviews = reviewRepository.findByAuthorName(authorName);
        if(reviews.isEmpty()) {
            throw new ReviewNotFoundException("Review with author name " + authorName + " not found");
        }
        return reviews;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorByName(String name) {
        if (name == null) {
            throw new AuthorIsNullException("Name is null");
        }
        List<Author> authors = authorRepository.findByName(name);
        if(authors.isEmpty()) {
            throw new AuthorNotFoundException("Author with name " + name + " not found");
        }
        return authors;
    }

    public Author getAuthorById(Long id) {
        if (id == null) {
            throw new AuthorIsNullException("Id is null");
        }
        Author author =  authorRepository.findById(id);
        if(author == null) {
            throw new AuthorNotFoundException("Author with id " + id + " not found");
        }
        return author;
    }
}
