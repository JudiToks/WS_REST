package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    
    public PostResponse createPost(PostCreateRequest request, String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(userOpt.get());
        post.setPublished(true);
        Post saved = postRepository.save(post);
        
        return new PostResponse(
            saved.getId(),
            saved.getTitle(),
            saved.getContent(),
            saved.getAuthor().getUsername(),
            saved.getCreatedAt()
        );
    }
    
    public List<Post> getAllPosts() {
        return postRepository.findByPublishedTrue(org.springframework.data.domain.Pageable.unpaged()).getContent();
    }
    
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
    
    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isEmpty()) {
            throw new RuntimeException("Post not found");
        }
        
        Post post = postOpt.get();
        if (request.getTitle() != null) {
            post.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            post.setContent(request.getContent());
        }
        
        Post updated = postRepository.save(post);
        return new PostResponse(
            updated.getId(),
            updated.getTitle(),
            updated.getContent(),
            updated.getAuthor().getUsername(),
            updated.getCreatedAt()
        );
    }
    
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
