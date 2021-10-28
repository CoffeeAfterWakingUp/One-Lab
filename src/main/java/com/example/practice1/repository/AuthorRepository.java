package com.example.practice1.repository;


import com.example.practice1.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByName(String name);


    @Query(value = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books b WHERE b.id=:bookId")
    List<Author> findAuthorsByBookId(@Param("bookId") Long bookId);


}
