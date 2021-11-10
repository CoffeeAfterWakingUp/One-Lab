package com.example.practice1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String surname;
}
