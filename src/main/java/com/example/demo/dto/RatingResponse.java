package com.example.demo.dto;

import java.time.LocalDateTime;

public class RatingResponse {
    private Long id;
    private Long postId;
    private Integer score;
    private String comment;
    private String author;
    private LocalDateTime createdAt;

    public RatingResponse() {}

    public RatingResponse(Long id, Long postId, Integer score, String comment, String author, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.score = score;
        this.comment = comment;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
