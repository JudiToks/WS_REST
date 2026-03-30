package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Tag(name = "Posts", description = "Blog post management")
public class PostController {
    private final PostService postService;
    
    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    @PostMapping
    @Operation(summary = "Create post", description = "Create a new blog post")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Post created",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class)))
    public PostResponse createPost(@RequestBody PostCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return postService.createPost(request, username);
    }
    
    @GetMapping
    @Operation(summary = "List posts", description = "Get all published posts")
    @ApiResponse(responseCode = "200", description = "Posts retrieved")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get post", description = "Get a specific post by ID")
    @ApiResponse(responseCode = "200", description = "Post retrieved")
    @ApiResponse(responseCode = "404", description = "Post not found")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id).orElse(null);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update post", description = "Update an existing post")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Post updated")
    public PostResponse updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        return postService.updatePost(id, request);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete post", description = "Delete a blog post")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Post deleted")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
