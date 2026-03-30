package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Lister les utilisateurs", description = "Récupérer tous les utilisateurs enregistrés")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un utilisateur", description = "Récupérer un utilisateur par son identifiant")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Obtenir un utilisateur par username", description = "Récupérer un utilisateur à partir de son nom d'utilisateur")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un utilisateur", description = "Modifier le profil d'un utilisateur")
    @SecurityRequirement(name = "bearerAuth")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur", description = "Supprimer un compte utilisateur")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}