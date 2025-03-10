package com.fico.instaserver.repository;

import com.fico.instaserver.model.Like;
import com.fico.instaserver.model.Post;
import com.fico.instaserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);
    void deleteByUserAndPost(User user, Post post);
    long countByPost(Post post);
}

