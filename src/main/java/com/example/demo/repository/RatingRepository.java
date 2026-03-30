package com.example.demo.repository;

import com.example.demo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByPostIdAndUserId(Long postId, Long userId);

    @Query("SELECT AVG(r.value) FROM Rating r WHERE r.post.id = ?1")
    Double getAverageRating(Long postId);

    Long countByPostId(Long postId);
}
