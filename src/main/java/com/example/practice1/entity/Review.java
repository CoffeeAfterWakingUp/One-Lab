package com.example.practice1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Long id;
    private String reviewerName;
    private Integer rating;
    private Book book;

}
