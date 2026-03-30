package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByPublishedTrue(Pageable pageable);

    Page<Post> findByAuthorUsernameAndPublishedTrue(String username, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.published = true ORDER BY p.viewCount DESC")
    Page<Post> findMostViewed(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.published = true ORDER BY SIZE(p.ratings) DESC")
    List<Post> findTopRated();

    Page<Post> findByTitleContainingIgnoreCaseAndPublishedTrue(String title, Pageable pageable);

    Page<Post> findByCategories_NameAndPublishedTrue(String categoryName, Pageable pageable);
}
