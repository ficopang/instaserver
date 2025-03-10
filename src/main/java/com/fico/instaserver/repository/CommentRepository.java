package com.fico.instaserver.repository;

import com.fico.instaserver.model.Comment;
import com.fico.instaserver.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    long countByPost(Post post);
}
