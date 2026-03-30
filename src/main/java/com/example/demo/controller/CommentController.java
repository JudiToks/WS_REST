package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentResponse;
import com.example.demo.dto.CommentUpdateRequest;
import com.example.demo.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comments")
@Tag(name = "Commentaires", description = "Gestion des commentaires")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @Operation(summary = "Créer un commentaire", description = "Ajouter un nouveau commentaire à un article")
    @SecurityRequirement(name = "bearerAuth")
    public CommentResponse createComment(@RequestBody CommentCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentService.createComment(request, username);
    }

    @GetMapping
    @Operation(summary = "Lister les commentaires", description = "Récupérer les commentaires d'un article spécifique")
    public List<CommentResponse> getCommentsByPost(@RequestParam Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un commentaire", description = "Récupérer un commentaire par son identifiant")
    public CommentResponse getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un commentaire", description = "Modifier un commentaire existant")
    @SecurityRequirement(name = "bearerAuth")
    public CommentResponse updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequest request) {
        return commentService.updateComment(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un commentaire", description = "Supprimer un commentaire par son identifiant")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
