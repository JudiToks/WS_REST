package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RatingCreateRequest;
import com.example.demo.dto.RatingResponse;
import com.example.demo.service.RatingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ratings")
@Tag(name = "Notes", description = "Gestion des notes des articles")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    @Operation(summary = "Noter un article", description = "Ajouter une note à un article de blog")
    @SecurityRequirement(name = "bearerAuth")
    public RatingResponse ratePost(@RequestBody RatingCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ratingService.ratePost(request, username);
    }

    @GetMapping
    @Operation(summary = "Lister les notes", description = "Récupérer les notes d’un article spécifique")
    public List<RatingResponse> getRatingsByPost(@RequestParam Long postId) {
        return ratingService.getRatingsByPost(postId);
    }

    @GetMapping("/average/{postId}")
    @Operation(summary = "Obtenir la note moyenne", description = "Calculer la note moyenne d’un article")
    public Double getAverageRating(@PathVariable Long postId) {
        return ratingService.getAverageRating(postId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une note", description = "Supprimer une note par son identifiant")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
}