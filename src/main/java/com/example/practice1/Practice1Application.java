package com.example.practice1;

import com.example.practice1.kafka.KafkaProducer;
import com.example.practice1.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Practice1Application implements CommandLineRunner {
    private static AuthorService authorService;
    private static KafkaProducer kafkaProducer;


    @Autowired
    public Practice1Application(AuthorService authorService, KafkaProducer kafkaProducer) {
        Practice1Application.authorService = authorService;
        Practice1Application.kafkaProducer = kafkaProducer;
    }




    public static void main(String[] args) {
        SpringApplication.run(Practice1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Long id = 1L;
//        Author author = authorService.getAuthorById(id);
//        kafkaProducer.sendAuthor(id, author.toString());
    }
}
