package com.example.practice1;

import com.example.practice1.service.AuthorService;
import com.example.practice1.service.BookService;
import com.example.practice1.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Practice1Application implements CommandLineRunner {
    private static AuthorService authorService;
    private static BookService bookService;
    private static ReviewService reviewService;


    @Autowired
    public Practice1Application(AuthorService authorService, BookService bookService, ReviewService reviewService) {
        Practice1Application.authorService = authorService;
        Practice1Application.bookService = bookService;
        Practice1Application.reviewService = reviewService;
    }


    public static void main(String[] args) {
        SpringApplication.run(Practice1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        runAuthorServiceMethods();
        runBookServiceMethods();
        runReviewServiceMethods();
    }

    private void runAuthorServiceMethods() {
        authorService.getAllAuthors().forEach(System.out::println);
        authorService.getAuthorByName("Stephen").forEach(System.out::println);
        System.out.println(authorService.getAuthorById(1L));
        authorService.getAuthorsByBookId(9L).forEach(System.out::println);
    }

    private void runBookServiceMethods() {
        bookService.getBooksByAuthorId(9L).forEach(System.out::println);
        bookService.getBooksByAuthorName("Stephen").forEach(System.out::println);
        bookService.getBooksByTitle("The Talisman").forEach(System.out::println);
        bookService.getAllBooks().forEach(System.out::println);
    }

    private void runReviewServiceMethods() {
        reviewService.getReviewsByAuthorName("Stephen").forEach(System.out::println);
        reviewService.getReviewsByBookTitle("The Talisman").forEach(System.out::println);
        reviewService.getReviewsByReviewerName("John Smith").forEach(System.out::println);
    }
}
