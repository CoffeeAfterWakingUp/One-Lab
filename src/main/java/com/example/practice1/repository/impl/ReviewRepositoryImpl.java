package com.example.practice1.repository.impl;

import com.example.practice1.database.Review;
import com.example.practice1.repository.ReviewRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    @Override
    public List<Review> findByReviewerName(String reviewerName) {
        return Review.getReviews().stream()
                .filter(review -> review.getReviewerName().equals(reviewerName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findByBookTitle(String title) {
        return Review.getReviews().stream()
                .filter(review -> review.getBook().getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findByAuthorName(String authorName) {
        return Review.getReviews().stream()
                .filter(review -> review.getBook().getAuthor().getName().equals(authorName))
                .collect(Collectors.toList());
    }
}
