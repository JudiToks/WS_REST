package com.example.demo.dto;



import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String content;
    private String author;
    private Boolean approved;
    private LocalDateTime createdAt;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Boolean getApproved() {
        return this.approved;
    }
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
