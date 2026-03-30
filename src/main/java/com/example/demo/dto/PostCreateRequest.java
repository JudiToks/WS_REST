package com.example.demo.dto;



public class PostCreateRequest {
    private String title;
    private String content;
    private String summary;
    private java.util.List<Long> categoryIds;
    private java.util.List<Long> tagIds;

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
    public java.util.List<Long> getCategoryIds() {
        return categoryIds;
    }
    public void setCategoryIds(java.util.List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
    public java.util.List<Long> getTagIds() {
        return tagIds;
    }
    public void setTagIds(java.util.List<Long> tagIds) {
        this.tagIds = tagIds;
    }
}
