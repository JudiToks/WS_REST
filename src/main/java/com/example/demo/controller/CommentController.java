package com.example.demo.controller;

import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentResponse;
import com.example.demo.dto.CommentUpdateRequest;
import com.example.demo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Tag(name = "Comments", description = "Comment management")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @Operation(summary = "Create comment", description = "Add a new comment to a post")
    @SecurityRequirement(name = "bearerAuth")
    public CommentResponse createComment(@RequestBody CommentCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentService.createComment(request, username);
    }

    @GetMapping
    @Operation(summary = "List comments", description = "Get comments for a specific post")
    public List<CommentResponse> getCommentsByPost(@RequestParam Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get comment", description = "Get a specific comment by ID")
    public CommentResponse getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update comment", description = "Update an existing comment")
    @SecurityRequirement(name = "bearerAuth")
    public CommentResponse updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequest request) {
        return commentService.updateComment(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete comment", description = "Delete a comment")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
