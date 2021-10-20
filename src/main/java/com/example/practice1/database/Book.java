package com.example.practice1.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private Genre genre;
    private Author author;

    public enum Genre {
        ADVENTURE, CLASSICS, HORROR, DETECTIVE, FANTASY
    }

    public static BookBuilder bookBuilder() {
        return new Book().new BookBuilder();
    }

    public class BookBuilder {
        private BookBuilder() {
        }

        public BookBuilder id(Long id) {
            Book.this.id = id;
            return this;
        }

        public BookBuilder title(String title) {
            Book.this.title = title;
            return this;
        }

        public BookBuilder genre(Genre genre) {
            Book.this.genre = genre;
            return this;
        }

        public BookBuilder author(Author author) {
            Book.this.author = author;
            return this;
        }

        public Book build() {
            return Book.this;
        }
    }

    public static List<Book> getBooks() {
        Book book1 = Book.bookBuilder()
                .id(1L)
                .title("Life of Pi")
                .genre(Genre.ADVENTURE)
                .author(new Author(1L, "Yann", "Martel"))
                .build();
        Book book2 = Book.bookBuilder()
                .id(2L)
                .title("The Call of the Wild")
                .genre(Genre.ADVENTURE)
                .author(new Author(1L, "Yann", "Martel"))
                .build();
        Book book3 = Book.bookBuilder()
                .id(3L)
                .title("To Kill a Mockingbird")
                .genre(Genre.CLASSICS)
                .author(new Author(2L, "Harper", "Lee"))
                .build();
        Book book4 = Book.bookBuilder()
                .id(4L)
                .title("Beloved")
                .genre(Genre.CLASSICS)
                .author(new Author(2L, "Harper", "Lee"))
                .build();
        Book book5 = Book.bookBuilder()
                .id(5L)
                .title("Carrie")
                .genre(Genre.HORROR)
                .author(new Author(3L, "Stephen", "King"))
                .build();
        Book book6 = Book.bookBuilder()
                .id(6L)
                .title("Bird Box")
                .genre(Genre.HORROR)
                .author(new Author(4L, "Josh", "Malerman"))
                .build();
        Book book7 = Book.bookBuilder()
                .id(7L)
                .title("The Adventures of Sherlock Holmes")
                .genre(Genre.DETECTIVE)
                .author(new Author(5L, "Sir Arthur Conan", "Doyle"))
                .build();
        Book book8 = Book.bookBuilder()
                .id(8L)
                .title("The Night Fire")
                .genre(Genre.DETECTIVE)
                .author(new Author(6L, "Michael", "Connelly"))
                .build();
        Book book9 = Book.bookBuilder()
                .id(9L)
                .title("The Water Dancer")
                .genre(Genre.FANTASY)
                .author(new Author(7L, "Madeline", "Miller"))
                .build();
        Book book10 = Book.bookBuilder()
                .id(10L)
                .title("Circe")
                .genre(Genre.FANTASY)
                .author(new Author(7L, "Madeline", "Miller"))
                .build();

        return Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10);
    }
}
