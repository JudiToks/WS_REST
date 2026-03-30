package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TagCreateRequest;
import com.example.demo.dto.TagResponse;
import com.example.demo.service.TagService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tags")
@Tag(name = "Tags", description = "Gestion des étiquettes")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @Operation(summary = "Créer un tag", description = "Créer une nouvelle étiquette")
    @SecurityRequirement(name = "bearerAuth")
    public TagResponse createTag(@RequestBody TagCreateRequest request) {
        return tagService.createTag(request);
    }

    @GetMapping
    @Operation(summary = "Lister les tags", description = "Récupérer toutes les étiquettes")
    public List<TagResponse> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un tag", description = "Récupérer une étiquette par son identifiant")
    public TagResponse getTagById(@PathVariable Long id) {
        return tagService.getTagById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un tag", description = "Modifier une étiquette existante")
    @SecurityRequirement(name = "bearerAuth")
    public TagResponse updateTag(@PathVariable Long id, @RequestBody TagCreateRequest request) {
        return tagService.updateTag(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un tag", description = "Supprimer une étiquette par son identifiant")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}