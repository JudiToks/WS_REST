package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CategoryCreateRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categories")
@Tag(name = "Catégories", description = "Gestion des catégories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Créer une catégorie", description = "Créer une nouvelle catégorie")
    @SecurityRequirement(name = "bearerAuth")
    public CategoryResponse createCategory(@RequestBody CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping
    @Operation(summary = "Lister les catégories", description = "Récupérer toutes les catégories")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une catégorie", description = "Récupérer une catégorie par son identifiant")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une catégorie", description = "Modifier une catégorie existante")
    @SecurityRequirement(name = "bearerAuth")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody CategoryCreateRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une catégorie", description = "Supprimer une catégorie par son identifiant")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}