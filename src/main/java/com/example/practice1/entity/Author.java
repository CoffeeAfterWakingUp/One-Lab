package com.example.practice1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;
    private String name;
    private String surname;
    //private List<Book> books = new ArrayList<>();


}
