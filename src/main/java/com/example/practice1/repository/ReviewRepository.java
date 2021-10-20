package com.example.practice1.repository;

import com.example.practice1.database.Review;

import java.util.List;

public interface ReviewRepository {

    List<Review> findByReviewerName(String reviewerName);

    List<Review> findByBookTitle(String title);

    List<Review> findByAuthorName(String authorName);
}
