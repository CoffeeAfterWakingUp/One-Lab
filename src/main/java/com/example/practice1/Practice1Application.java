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
        System.out.println(service.getAllAuthors());
        System.out.println(service.getAuthorByName("Stephen"));
        System.out.println(service.getAuthorById(5L));
        System.out.println("------------------------------------");
        System.out.println(service.getBooksByAuthorId(9L));
        System.out.println(service.getBooksByAuthorName("Stephen"));
        System.out.println(service.getBooksByTitle("The Talisman"));
        System.out.println(service.getAllBooks());
        System.out.println("------------------------------------");
        System.out.println(service.getReviewsByAuthorName("Stephen"));
        System.out.println(service.getReviewsByBookTitle("The Talisman"));
        System.out.println(service.getReviewsByReviewerName("John Smith"));

    }
}
