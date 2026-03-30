package com.example.demo.controller;

import com.example.demo.dto.RatingCreateRequest;
import com.example.demo.dto.RatingResponse;
import com.example.demo.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@Tag(name = "Ratings", description = "Rating management")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    @Operation(summary = "Rate post", description = "Rate a blog post")
    @SecurityRequirement(name = "bearerAuth")
    public RatingResponse ratePost(@RequestBody RatingCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ratingService.ratePost(request, username);
    }

    @GetMapping
    @Operation(summary = "List ratings", description = "Get ratings for a specific post")
    public List<RatingResponse> getRatingsByPost(@RequestParam Long postId) {
        return ratingService.getRatingsByPost(postId);
    }

    @GetMapping("/average/{postId}")
    @Operation(summary = "Get average rating", description = "Get average rating for a post")
    public Double getAverageRating(@PathVariable Long postId) {
        return ratingService.getAverageRating(postId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete rating", description = "Delete a rating")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
}
