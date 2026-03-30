package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PostCreateRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.dto.PostUpdateRequest;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/posts")
@Tag(name = "Articles", description = "Gestion des articles du blog")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @Operation(summary = "Créer un article", description = "Créer un nouvel article de blog")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Article créé", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class)))
    public PostResponse createPost(@RequestBody PostCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return postService.createPost(request, username);
    }

    @GetMapping
    @Operation(summary = "Lister les articles", description = "Récupérer tous les articles publiés")
    @ApiResponse(responseCode = "200", description = "Articles récupérés")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un article", description = "Récupérer un article par son identifiant")
    @ApiResponse(responseCode = "200", description = "Article récupéré")
    @ApiResponse(responseCode = "404", description = "Article introuvable")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un article", description = "Modifier un article existant")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Article mis à jour")
    public PostResponse updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        return postService.updatePost(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un article", description = "Supprimer un article du blog")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Article supprimé")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}