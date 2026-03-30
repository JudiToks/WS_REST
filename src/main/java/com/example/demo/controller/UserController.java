package com.example.demo.controller;

import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "User management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "List users", description = "Get all registered users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user", description = "Get a specific user by ID")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username", description = "Get a specific user by their username")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update user profile")
    @SecurityRequirement(name = "bearerAuth")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Delete a user account")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
