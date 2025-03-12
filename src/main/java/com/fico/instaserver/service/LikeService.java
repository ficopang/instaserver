package com.fico.instaserver.service;

import com.fico.instaserver.model.*;
import com.fico.instaserver.repository.CommentRepository;
import com.fico.instaserver.repository.LikeRepository;
import com.fico.instaserver.repository.PostRepository;
import com.fico.instaserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

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

    public List<LikeResponse> getLikes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return likeRepository.findLikeByPostOwner(user.getId()).stream().map(
                (like -> new LikeResponse(like.getUser().getUsername(), new PostResponse(like.getPost(), like.getPost().getUser(), likeRepository.countByPost(like.getPost()), commentRepository.countByPost(like.getPost()), likeRepository.existsByUserAndPost(user, like.getPost()))))
        ).collect(Collectors.toList());
    }
}

