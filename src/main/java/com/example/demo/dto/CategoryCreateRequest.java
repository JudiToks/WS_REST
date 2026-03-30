package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryCreateRequest {
    @NotBlank(message = "Category name is required")
    private String name;
    
    private String description;

    // Default constructor for Jackson
    public CategoryCreateRequest() {}

    public CategoryCreateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
