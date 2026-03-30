package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentUpdateRequest {
    @NotBlank(message = "Comment content is required")
    private String content;

    public CommentUpdateRequest() {}

    public CommentUpdateRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
