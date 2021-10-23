package com.example.practice1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private Genre genre;
    private List<Author> authors = new ArrayList<>();
    //private List<Review> reviews = new ArrayList<>();

    public enum Genre {
        ADVENTURE, CLASSICS, HORROR, DETECTIVE, FANTASY
    }


}
