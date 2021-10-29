package com.example.practice1.service;

import com.example.practice1.entity.Review;
import com.example.practice1.exception.ReviewIsNullException;
import com.example.practice1.exception.ReviewNotFoundException;
import com.example.practice1.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class ReviewServiceTest {
    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewService ut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetReviewsByReviewerName() {
        List<Review> reviews = Arrays.asList(
                new Review(1L, "Reviewer1", 5),
                new Review(2L, "Reviewer1", 6),
                new Review(3L, "Reviewer1", 7));
        when(reviewRepository.findByReviewerName(anyString())).thenReturn(reviews);
        List<Review> result = ut.getReviewsByReviewerName("Reviewer1");
        assertEquals(result, reviews);
    }

    @Test
    void givenNullToReviewerName_whenGetReviewsByReviewerName_thenThrowReviewIsNullException() {
        Exception exception = assertThrows(ReviewIsNullException.class, () -> ut.getReviewsByReviewerName(null));

        String expectedMessage = "Reviewer Name is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenFoundReviewsIsEmpty_whenGetReviewsByReviewerName_thenThrowReviewNotFoundException() {
        String wrongReviewerName = "Wrong Reviewer";
        Exception exception = assertThrows(ReviewNotFoundException.class, () -> ut.getReviewsByReviewerName(wrongReviewerName));

        String expectedMessage = "Review with reviewer name " + wrongReviewerName + " not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGetReviewsByBookTitle() {
        List<Review> reviews = Arrays.asList(
                new Review(1L, "Reviewer1", 5),
                new Review(2L, "Reviewer2", 6),
                new Review(3L, "Reviewer3", 7));
        when(reviewRepository.findByBookTitle(anyString())).thenReturn(reviews);
        List<Review> result = ut.getReviewsByBookTitle("bookTitle");
        assertEquals(result, reviews);
    }

    @Test
    void givenNullToBookTitle_whenGetReviewsByBookTitle_thenThrowReviewIsNullException() {
        Exception exception = assertThrows(ReviewIsNullException.class, () -> ut.getReviewsByBookTitle(null));

        String expectedMessage = "Book title is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenFoundReviewsIsEmpty_whenGetReviewsByBookTitle_thenThrowReviewNotFoundException() {
        String wrongBookTitle = "Wrong Title";
        Exception exception = assertThrows(ReviewNotFoundException.class, () -> ut.getReviewsByBookTitle(wrongBookTitle));

        String expectedMessage = "Review with book title " + wrongBookTitle + " not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGetReviewsByAuthorName() {
        List<Review> reviews = Arrays.asList(
                new Review(1L, "Reviewer1", 5),
                new Review(2L, "Reviewer2", 6));
        when(reviewRepository.findByAuthorName(anyString())).thenReturn(reviews);
        List<Review> result = ut.getReviewsByAuthorName("authorName");
        assertEquals(result, reviews);
    }

    @Test
    void givenNullToAuthorName_whenGetReviewsByAuthorName_thenThrowReviewIsNullException() {
        Exception exception = assertThrows(ReviewIsNullException.class, () -> ut.getReviewsByAuthorName(null));

        String expectedMessage = "Author name is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenFoundReviewsIsEmpty_whenGetReviewsByAuthorName_thenThrowReviewNotFoundException() {
        String wrongAuthor = "Wrong Author Name";
        Exception exception = assertThrows(ReviewNotFoundException.class, () -> ut.getReviewsByAuthorName(wrongAuthor));

        String expectedMessage = "Review with author name " + wrongAuthor + " not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme