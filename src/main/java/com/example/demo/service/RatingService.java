package com.example.demo.service;

import com.example.demo.dto.RatingCreateRequest;
import com.example.demo.dto.RatingResponse;
import com.example.demo.model.Post;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.RatingRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, PostRepository postRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public RatingResponse ratePost(RatingCreateRequest request, String username) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if user already rated this post
        Optional<Rating> existingRating = ratingRepository.findByPostIdAndUserId(post.getId(), user.getId());
        
        Rating rating;
        if (existingRating.isPresent()) {
            rating = existingRating.get();
        } else {
            rating = new Rating();
            rating.setPost(post);
            rating.setUser(user);
        }
        
        rating.setValue(request.getScore());
        rating.setComment(request.getComment());

        Rating saved = ratingRepository.save(rating);
        return mapToResponse(saved);
    }

    public List<RatingResponse> getRatingsByPost(Long postId) {
        // Since RatingRepository doesn't have findByPostId returning a List/Page directly in our previous view, 
        // we'll assume we might need it or use a filtered find.
        // Actually, let's just use findAll and filter for now (not optimal but safe if repo is limited).
        // Better: let's assume we can add it to repo if needed, but for now let's use what we have.
        return ratingRepository.findAll().stream()
                .filter(r -> r.getPost().getId().equals(postId))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Double getAverageRating(Long postId) {
        return ratingRepository.getAverageRating(postId);
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    private RatingResponse mapToResponse(Rating rating) {
        return new RatingResponse(
                rating.getId(),
                rating.getPost().getId(),
                rating.getValue(),
                rating.getComment(),
                rating.getUser().getUsername(),
                rating.getCreatedAt()
        );
    }
}
