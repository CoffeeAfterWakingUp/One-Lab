package com.example.practice1.service;

import com.example.practice1.entity.Author;
import com.example.practice1.exception.AuthorIsNullException;
import com.example.practice1.exception.AuthorNotFoundException;
import com.example.practice1.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorService ut;


    @Test
    void testGetAllAuthors() {
        List<Author> authors = Arrays.asList(new Author(9L, "Stephen", "King"),
                new Author(10L, "Shirley", "Jackson"),
                new Author(11L, "Peter", "Straub"));
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = ut.getAllAuthors();

        assertEquals(authors, result);
    }

    @Test
    void testGetAuthorByName() {
        List<Author> authorsFoundByName = Collections.singletonList(new Author(9L, "Stephen", "King"));

        when(authorRepository.findByName(anyString())).thenReturn(authorsFoundByName);

        List<Author> result = ut.getAuthorByName("Stephen");
        assertEquals(authorsFoundByName, result);
    }

    @Test
    void givenNullToName_callingGetAuthorByName_shouldThrowAuthorIsNullException() {
        Exception exception = assertThrows(AuthorIsNullException.class, () -> ut.getAuthorByName(null));

        String expectedMessage = "Name is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenFoundAuthorsIsEmpty_callingGetAuthorByName_when_shouldThrowAuthorNotFoundException() {
        Exception exception = assertThrows(AuthorNotFoundException.class, () -> ut.getAuthorByName("Maya"));

        String expectedMessage = "Author with name Maya not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void testGetAuthorById() {
        Author author = new Author(9L, "Stephen", "King");
        when(authorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(author));

        Author result = ut.getAuthorById(9L);

        assertEquals(author, result);

    }

    @Test
    void givenNullToId_whenCallingGetAuthorById_thenShouldThrowAuthorIsNullException() {
        Exception exception = assertThrows(AuthorIsNullException.class, () -> ut.getAuthorById(null));

        String expectedMessage = "Id is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void givenFoundAuthorIsEmpty_whenCallingGetAuthorById_thenShouldThrowAuthorNotFoundException() {
        Long wrongAuthorId = 45L;
        Exception exception = assertThrows(AuthorNotFoundException.class, () -> ut.getAuthorById(wrongAuthorId));

        String expectedMessage = "Author with id " + wrongAuthorId + " not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void testGetAuthorsByBookId() {
        List<Author> authors = Arrays.asList(new Author(9L, "Stephen", "King"),
                new Author(11L, "Peter", "Straub"));
        when(authorRepository.findAuthorsByBookId(anyLong())).thenReturn(authors);
        List<Author> result = ut.getAuthorsByBookId(11L);
        assertEquals(result, authors);
    }

    @Test
    void testCreateAuthor() {
        Author author = new Author(null, "Author", "Author");
        when(authorRepository.save(author)).thenReturn(author);
        Author result = ut.createAuthor(author);
        assertEquals(author, result);
    }

    @Test
    void givenAuthorIsNull_whenCallingCreateAuthor_thenShouldThrowAuthorIsNullException() {
        Exception exception = assertThrows(AuthorIsNullException.class, () -> ut.createAuthor(null));

        String expectedMessage = "Author Is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void givenAuthorIsNull_whenCallingUpdateAuthor_thenShouldThrowAuthorIsNullException() {
        Exception exception = assertThrows(AuthorIsNullException.class, () -> ut.updateAuthor(null, 0L));

        String expectedMessage = "Author Is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void givenIdIsNull_whenCallingUpdateAuthor_thenShouldThrowAuthorIsNullException() {
        Exception exception = assertThrows(AuthorIsNullException.class, () -> ut.deleteAuthor(null));

        String expectedMessage = "Id is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author(0L, "Author", "Author");
        when(authorRepository.save(author)).thenReturn(author);
        Author result = ut.createAuthor(author);
        assertEquals(author, result);
    }

    @Test
    void testDeleteAuthor() {
        Long id = 0L;
        when(authorRepository.findById(id)).thenReturn(Optional.of(new Author()));
        doNothing().when(authorRepository).deleteById(id);
        ut.deleteAuthor(id);
        verify(authorRepository, times(1)).deleteById(id);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme