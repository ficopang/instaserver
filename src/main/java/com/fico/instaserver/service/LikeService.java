package com.fico.instaserver.service;

import com.fico.instaserver.model.Like;
import com.fico.instaserver.model.Post;
import com.fico.instaserver.model.User;
import com.fico.instaserver.repository.LikeRepository;
import com.fico.instaserver.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public String toggleLike(Long postId, String username) {
        User user = userService.getUserByUsername(username);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (likeRepository.existsByUserAndPost(user, post)) {
            likeRepository.deleteByUserAndPost(user, post);
            return "Post unliked";
        } else {
            likeRepository.save(new Like(user, post));
            return "Post liked";
        }
    }
}

