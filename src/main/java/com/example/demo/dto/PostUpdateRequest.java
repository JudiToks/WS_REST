package com.example.demo.dto;



import java.util.Set;

public class PostUpdateRequest {
    private String title;
    private String content;
    private String summary;
    private Set<Long> categoryIds;
    private Set<Long> tagIds;
    private Boolean published;

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
    public Set<Long> getCategoryIds() {
        return this.categoryIds;
    }
    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
    public Set<Long> getTagIds() {
        return this.tagIds;
    }
    public void setTagIds(Set<Long> tagIds) {
        this.tagIds = tagIds;
    }
    public Boolean getPublished() {
        return this.published;
    }
    public void setPublished(Boolean published) {
        this.published = published;
    }
}
