package com.example.practice1;

import com.example.practice1.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Practice1Application implements CommandLineRunner {
    private static Service service;

    @Autowired
    public Practice1Application(Service service) {
        Practice1Application.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Practice1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.getAllAuthors();
        service.getAuthorByName("Stephen");
        service.getAuthorById(5L);
        service.getBooksByAuthorId(9L);
        service.getBooksByAuthorName("Stephen");
        service.getBooksByTitle("The Talisman");
        service.getAllBooks();
        service.getReviewsByAuthorName("Stephen");
        service.getReviewsByBookTitle("The Talisman");
        service.getReviewsByReviewerName("John Smith");
        service.getAuthorsByBookId(9L);
        //service.getAuthorsByBookId(null).forEach(System.out::println);
        //service.getBooksByAuthorId(null);

    }
}
