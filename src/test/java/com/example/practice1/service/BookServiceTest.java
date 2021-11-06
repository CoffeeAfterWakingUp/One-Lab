package com.example.practice1.service;

import com.example.practice1.entity.Book;
import com.example.practice1.exception.AuthorIsNullException;
import com.example.practice1.exception.BookIsNullException;
import com.example.practice1.exception.BookNotFoundException;
import com.example.practice1.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService ut;


    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "dummyTitle1", Book.Genre.ADVENTURE),
                new Book(2L, "dummyTitle2", Book.Genre.ADVENTURE),
                new Book(3L, "dummyTitle3", Book.Genre.ADVENTURE));
        when(bookRepository.findAll()).thenReturn(books);
        List<Book> result = ut.getAllBooks();
        assertEquals(result, books);
    }

    @Test
    void testGetBooksByTitle() {
        List<Book> books = Arrays.asList(
                new Book(1L, "dummyTitle", Book.Genre.ADVENTURE),
                new Book(2L, "dummyTitle", Book.Genre.CLASSICS));
        when(bookRepository.findByTitle(anyString())).thenReturn(books);
        List<Book> result = ut.getBooksByTitle("dummyTitle");
        assertEquals(result, books);
    }

    @Test
    void givenNullToTitle_whenGetBooksByTitle_thenThrowBookIsNullException() {
        Exception exception = assertThrows(BookIsNullException.class, () -> ut.getBooksByTitle(null));

        String expectedMessage = "Book Title is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenFoundBooksIsEmpty_whenGetBooksByTitle_thenThrowBookNotFoundException() {
        String wrongBookTitle = "Asasasa";
        Exception exception = assertThrows(BookNotFoundException.class, () -> ut.getBooksByTitle(wrongBookTitle));

        String expectedMessage = "Book with title " + wrongBookTitle + " not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGetBooksByAuthorName() {
        List<Book> books = Arrays.asList(
                new Book(1L, "dummyTitle1", Book.Genre.HORROR),
                new Book(2L, "dummyTitle2", Book.Genre.HORROR));
        when(bookRepository.findBooksByAuthorName(anyString())).thenReturn(books);
        List<Book> result = ut.getBooksByAuthorName("Stephen");
        assertEquals(result, books);
    }

    @Test
    void givenNullToAuthorName_whenGetBooksByAuthorName_thenThrowBookIsNullException() {
        Exception exception = assertThrows(BookIsNullException.class, () -> ut.getBooksByAuthorName(null));

        String expectedMessage = "Author Name is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenFoundBooksIsEmpty_whenGetBooksByAuthorName_thenThrowBookNotFoundException() {
        String wrongAuthorName = "Abaaac";
        Exception exception = assertThrows(BookNotFoundException.class, () -> ut.getBooksByAuthorName(wrongAuthorName));

        String expectedMessage = "Books with author " + wrongAuthorName + " not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGetBooksByAuthorId() {
        List<Book> books = Arrays.asList(
                new Book(1L, "dummyTitle1", Book.Genre.HORROR),
                new Book(2L, "dummyTitle2", Book.Genre.HORROR));
        when(bookRepository.findBooksByAuthorId(anyLong())).thenReturn(books);
        List<Book> result = ut.getBooksByAuthorId(9L);
        assertEquals(result, books);
    }

    @Test
    void givenNullToAuthorId_whenGetBooksByAuthorId_thenThrowAuthorIsNullException() {
        Exception exception = assertThrows(AuthorIsNullException.class, () -> ut.getBooksByAuthorId(null));

        String expectedMessage = "Id is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme