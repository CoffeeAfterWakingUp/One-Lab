package com.example.practice1.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Long id;
    private String name;
    private String surname;

    public static AuthorBuilder authorBuilder() {
        return new Author().new AuthorBuilder();
    }

    public class AuthorBuilder {
        private AuthorBuilder() {
        }

        public AuthorBuilder id(Long id) {
            Author.this.id = id;
            return this;
        }

        public AuthorBuilder name(String name) {
            Author.this.name = name;
            return this;
        }

        public AuthorBuilder surname(String surname) {
            Author.this.surname = surname;
            return this;
        }

        public Author build() {
            return Author.this;
        }
    }

    public static List<Author> getAuthors() {
        Author author1 = Author.authorBuilder()
                .id(1L)
                .name("Yann")
                .surname("Martel")
                .build();
        Author author2 = Author.authorBuilder()
                .id(2L)
                .name("Harper")
                .surname("Lee")
                .build();
        Author author3 = Author.authorBuilder()
                .id(3L)
                .name("Stephen")
                .surname("King")
                .build();
        Author author4 = Author.authorBuilder()
                .id(4L)
                .name("Josh")
                .surname("Malerman")
                .build();
        Author author5 = Author.authorBuilder()
                .id(5L)
                .name("Sir Arthur Conan")
                .surname("Doyle")
                .build();
        Author author6 = Author.authorBuilder()
                .id(6L)
                .name("Michael")
                .surname("Connelly")
                .build();
        Author author7 = Author.authorBuilder()
                .id(7L)
                .name("Madeline")
                .surname("Miller")
                .build();

        return Arrays.asList(author1, author2, author3, author4, author5, author6, author7);
    }

}
