package com.example.practice1.repository;

import com.example.practice1.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String title);


    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE a.name=:authorName")
    List<Book> findBooksByAuthorName(@Param("authorName") String authorName);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE a.id=:authorId")
    List<Book> findBooksByAuthorId(@Param("authorId") Long authorId);

}
