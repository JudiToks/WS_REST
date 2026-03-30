package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class TagCreateRequest {
    @NotBlank(message = "Tag name is required")
    private String name;

    public TagCreateRequest() {}

    public TagCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
