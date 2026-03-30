package com.example.demo.controller;

import com.example.demo.dto.TagCreateRequest;
import com.example.demo.dto.TagResponse;
import com.example.demo.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Tag(name = "Tags", description = "Tag management")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @Operation(summary = "Create tag", description = "Create a new tag (Admin only conceptually)")
    @SecurityRequirement(name = "bearerAuth")
    public TagResponse createTag(@RequestBody TagCreateRequest request) {
        return tagService.createTag(request);
    }

    @GetMapping
    @Operation(summary = "List tags", description = "Get all tags")
    public List<TagResponse> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get tag", description = "Get a specific tag by ID")
    public TagResponse getTagById(@PathVariable Long id) {
        return tagService.getTagById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update tag", description = "Update an existing tag")
    @SecurityRequirement(name = "bearerAuth")
    public TagResponse updateTag(@PathVariable Long id, @RequestBody TagCreateRequest request) {
        return tagService.updateTag(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete tag", description = "Delete a tag")
    @SecurityRequirement(name = "bearerAuth")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }
}
