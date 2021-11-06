package com.example.practice1.rest;

import com.example.practice1.entity.Author;
import com.example.practice1.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/authors")
@CrossOrigin(origins = "*")
public class AuthorRestController {

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(value = "name/{name}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAuthorByName(@PathVariable("name") String name) {
        return authorService.getAuthorByName(name);
    }

    @GetMapping(value = "/book/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAuthorsByBookId(@PathVariable("bookId") Long bookId) {
        return authorService.getAuthorsByBookId(bookId);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@PathVariable("id") Long id,
                               @RequestBody Author author) {
        return authorService.updateAuthor(author, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }


}
