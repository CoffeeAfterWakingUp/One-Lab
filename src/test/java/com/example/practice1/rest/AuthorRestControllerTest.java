package com.example.practice1.rest;

import com.example.practice1.entity.Author;
import com.example.practice1.exception.AuthorNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(value = "olzhik")
class AuthorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void givenId_whenGetAuthorById_thenShouldReturnAuthor() throws Exception {
        String expected = "" +
                "{" +
                "\"id\":1," +
                "\"name\":\"Yann\"," +
                "\"surname\":\"Martel\"" +
                "}";

        this.mockMvc.perform(get("/api/authors/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expected));
    }

    @Test
    void givenWrongId_whenGetAuthorById_thenShouldThrowAuthorNotFoundException() throws Exception {
        String wrongId = "1221";
        String expectedExceptionMessage = "No Author with id " + wrongId;
        this.mockMvc.perform(get("/api/authors/" + wrongId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AuthorNotFoundException))
                .andExpect(result -> assertEquals(expectedExceptionMessage, Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    void whenGetAuthors_thenShouldReturnAllAuthors() throws Exception {
        this.mockMvc.perform(get("/api/authors/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void givenAuthorName_whenGetAuthorByName_thenShouldReturnAuthors() throws Exception {
        String expected = "[" +
                "{" +
                "\"id\":1," +
                "\"name\":\"Yann\"," +
                "\"surname\":\"Martel\"" +
                "}]";
        this.mockMvc.perform(get("/api/authors/name/Yann"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expected));

    }


    @Test
    void givenBookId_whenGetAuthorsByBookId_thenShouldReturnAuthors() throws Exception {
        String expected = "[" +
                "{" +
                "\"id\":1," +
                "\"name\":\"Yann\"," +
                "\"surname\":\"Martel\"" +
                "}]";
        this.mockMvc.perform(get("/api/authors/book/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expected));
    }

    @Test
    void givenAuthor_whenCreateAuthor_thenShouldReturnCreatedAuthor() throws Exception {
        Author author = new Author(null, "dummy", "dummy");

        this.mockMvc.perform(post("/api/authors/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(author)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(author.getName()))
                .andExpect(jsonPath("$.surname").value(author.getSurname()));
    }

    @Test
    void givenAuthor_whenUpdateAuthor_thenShouldReturnUpdatedAuthor() throws Exception {
        Author author = new Author(2L, "updatedDummy", "updatedDummy");

        this.mockMvc.perform(put("/api/authors/2/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(author)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(author.getId()))
                .andExpect(jsonPath("$.name").value(author.getName()))
                .andExpect(jsonPath("$.surname").value(author.getSurname()));
    }


    @Test
    void givenId_whenDeleteAuthor_thenShouldDeleteAuthor() throws Exception {
        this.mockMvc.perform(delete("/api/authors/2/"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    static String toJson(Author author) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(author);
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme