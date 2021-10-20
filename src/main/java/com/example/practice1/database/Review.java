package com.example.practice1.database;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Review {
    private Long id;
    private String reviewerName;
    private Integer rating;
    private Book book;

    public static ReviewBuilder reviewBuilder() {
        return new Review().new ReviewBuilder();
    }

    public class ReviewBuilder {
        private ReviewBuilder() {
        }

        public ReviewBuilder id(Long id) {
            Review.this.id = id;
            return this;
        }

        public ReviewBuilder reviewerName(String reviewerName) {
            Review.this.reviewerName = reviewerName;
            return this;
        }

        public ReviewBuilder rating(Integer rating) {
            Review.this.rating = rating;
            return this;
        }

        public ReviewBuilder book(Book book) {
            Review.this.book = book;
            return this;
        }

        public Review build() {
            return Review.this;
        }
    }

    public static List<Review> getReviews() {
        Review review1 = Review.reviewBuilder()
                .id(1L)
                .reviewerName("John Smith")
                .rating(5)
                .book(new Book(1L, "Life of Pi", Book.Genre.ADVENTURE, new Author(1L, "Yann", "Martel")))
                .build();
        Review review2 = Review.reviewBuilder()
                .id(2L)
                .reviewerName("John Smith")
                .rating(4)
                .book(new Book(2L, "The Call of the Wild", Book.Genre.ADVENTURE, new Author(1L, "Yann", "Martel")))
                .build();
        Review review3 = Review.reviewBuilder()
                .id(3L)
                .reviewerName("Alice Walker")
                .rating(10)
                .book(new Book(2L, "The Call of the Wild", Book.Genre.ADVENTURE, new Author(1L, "Yann", "Martel")))
                .build();
        Review review4 = Review.reviewBuilder()
                .id(4L)
                .reviewerName("Boris Talker")
                .rating(8)
                .book(new Book(3L, "To Kill a Mockingbird", Book.Genre.CLASSICS, new Author(2L, "Harper", "Lee")))
                .build();

        return Arrays.asList(review1, review2, review3, review4);

    }
}
