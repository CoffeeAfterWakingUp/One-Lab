package com.example.practice1.rest;

import com.example.practice1.dto.AuthorDTO;
import com.example.practice1.entity.Author;
import com.example.practice1.service.AuthorService;
import com.example.practice1.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/authors")
@CrossOrigin(origins = "*")
public class AuthorRestController {

    private final AuthorService authorService;
    private final ModelMapper modelMapper;
    private final BookService bookService;

    @Autowired
    public AuthorRestController(AuthorService authorService, ModelMapper modelMapper, BookService bookService) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO getAuthorById(@PathVariable("id") Long id) {
        Author author = authorService.getAuthorById(id);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors()
                .stream()
                .map(a -> modelMapper.map(a, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "name/{name}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> getAuthorByName(@PathVariable("name") String name) {
        return authorService.getAuthorByName(name)
                .stream()
                .map(a -> modelMapper.map(a, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/book/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> getAuthorsByBookId(@PathVariable("bookId") Long bookId) {
        return authorService.getAuthorsByBookId(bookId)
                .stream()
                .map(a -> modelMapper.map(a, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        Author createdAuthor = authorService.createAuthor(author);
        return modelMapper.map(createdAuthor, AuthorDTO.class);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO updateAuthor(@PathVariable("id") Long id,
                                  @RequestBody AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        author.setBooks(new HashSet<>(bookService.getBooksByAuthorId(id)));
        Author updatedAuthor = authorService.updateAuthor(author, id);
        return modelMapper.map(updatedAuthor, AuthorDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }


}
