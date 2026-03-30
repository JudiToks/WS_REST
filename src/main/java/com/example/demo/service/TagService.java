package com.example.demo.service;

import com.example.demo.dto.TagCreateRequest;
import com.example.demo.dto.TagResponse;
import com.example.demo.model.Tag;
import com.example.demo.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagResponse createTag(TagCreateRequest request) {
        if (tagRepository.existsByName(request.getName())) {
            throw new RuntimeException("Tag already exists");
        }

        Tag tag = new Tag();
        tag.setName(request.getName());

        Tag saved = tagRepository.save(tag);
        return mapToResponse(saved);
    }

    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<TagResponse> getTagById(Long id) {
        return tagRepository.findById(id)
                .map(this::mapToResponse);
    }

    public TagResponse updateTag(Long id, TagCreateRequest request) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        tag.setName(request.getName());

        Tag updated = tagRepository.save(tag);
        return mapToResponse(updated);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    private TagResponse mapToResponse(Tag tag) {
        return new TagResponse(
                tag.getId(),
                tag.getName()
        );
    }
}
