package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentCreateRequest {
    @NotNull(message = "Post ID is required")
    private Long postId;

    @NotBlank(message = "Comment content is required")
    private String content;

    public CommentCreateRequest() {}

    public CommentCreateRequest(Long postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
