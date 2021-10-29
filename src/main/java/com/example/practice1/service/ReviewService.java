package com.example.practice1.service;

import com.example.practice1.entity.Review;
import com.example.practice1.exception.ReviewIsNullException;
import com.example.practice1.exception.ReviewNotFoundException;
import com.example.practice1.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByReviewerName(String reviewerName) {
        if (reviewerName == null) {
            throw new ReviewIsNullException("Reviewer Name is null");
        }
        List<Review> reviews = reviewRepository.findByReviewerName(reviewerName);
        if (reviews.isEmpty()) {
            throw new ReviewNotFoundException("Review with reviewer name " + reviewerName + " not found");
        }
        return reviews;
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByBookTitle(String bookTitle) {
        if (bookTitle == null) {
            throw new ReviewIsNullException("Book title is null");
        }
        List<Review> reviews = reviewRepository.findByBookTitle(bookTitle);
        if (reviews.isEmpty()) {
            throw new ReviewNotFoundException("Review with book title " + bookTitle + " not found");
        }
        return reviews;
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByAuthorName(String authorName) {
        if (authorName == null) {
            throw new ReviewIsNullException("Author name is null");
        }
        List<Review> reviews = reviewRepository.findByAuthorName(authorName);
        if (reviews.isEmpty()) {
            throw new ReviewNotFoundException("Review with author name " + authorName + " not found");
        }
        return reviews;
    }
}
