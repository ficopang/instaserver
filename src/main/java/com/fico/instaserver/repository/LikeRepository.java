package com.fico.instaserver.repository;

import com.fico.instaserver.model.Like;
import com.fico.instaserver.model.Post;
import com.fico.instaserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);
    void deleteByUserAndPost(User user, Post post);
    long countByPost(Post post);
    @Query("SELECT l FROM Like l WHERE l.post.user.id = :userId")
    List<Like> findLikeByPostOwner(Long userId);
}

