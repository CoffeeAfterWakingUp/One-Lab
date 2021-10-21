package com.example.practice1;

import com.example.practice1.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Practice1Application {
    private static Service service;

    @Autowired
    public Practice1Application(Service service) {
        Practice1Application.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Practice1Application.class, args);
        service.getAllBooks().forEach(System.out::println);
        System.out.println(service.getBookByTitle("Life of Pi"));
        System.out.println(service.getBooksByAuthorName("Yann"));
        service.getReviewsByBookTitle("The Call of the Wild").forEach(System.out::println);
        service.getReviewsByReviewerName("John Smith").forEach(System.out::println);
         service.getReviewsByAuthorName("Yann").forEach(System.out::println);
        service.getAllAuthors().forEach(System.out::println);
        service.getAuthorByName("Yann").forEach(System.out::println);
        System.out.println(service.getAuthorById(2L));
    }
}
