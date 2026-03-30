package com.example.demo.dto;



import java.time.LocalDateTime;
import java.util.Set;

public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String summary;
    private String author;
    private Set<String> categories;
    private Set<String> tags;
    private Integer commentCount;
    private Double averageRating;
    private Integer ratingCount;
    private Integer viewCount;
    private Boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponse() {
    }

    public PostResponse(Long id, String title, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Set<String> getCategories() {
        return this.categories;
    }
    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
    public Set<String> getTags() {
        return this.tags;
    }
    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
    public Integer getCommentCount() {
        return this.commentCount;
    }
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
    public Double getAverageRating() {
        return this.averageRating;
    }
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
    public Integer getRatingCount() {
        return this.ratingCount;
    }
    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }
    public Integer getViewCount() {
        return this.viewCount;
    }
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
    public Boolean getPublished() {
        return this.published;
    }
    public void setPublished(Boolean published) {
        this.published = published;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
