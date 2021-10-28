package com.example.practice1.repository;

import com.example.practice1.entity.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByReviewerName(String reviewerName);

    List<Review> findByBookTitle(String bookTitle);

    @Query("SELECT r FROM Review r LEFT JOIN FETCH r.book b LEFT JOIN FETCH b.authors a WHERE a.name=:authorName")
    List<Review> findByAuthorName(@Param("authorName") String authorName);
}
