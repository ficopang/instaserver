package com.fico.instaserver.repository;

import com.fico.instaserver.model.Post;
import com.fico.instaserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
    List<Post> findAllByUser(User user);
}
