package com.fico.instaserver.service;

import com.fico.instaserver.model.Post;
import com.fico.instaserver.model.PostResponse;
import com.fico.instaserver.model.ProfileResponse;
import com.fico.instaserver.model.User;
import com.fico.instaserver.repository.CommentRepository;
import com.fico.instaserver.repository.LikeRepository;
import com.fico.instaserver.repository.PostRepository;
import com.fico.instaserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    CommentRepository commentRepository;

    public ProfileResponse getProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Post> posts = postRepository.findAllByUser(user);

        List<PostResponse> postResponses = posts.stream()
                .map(post -> new PostResponse(post, post.getUser(), likeRepository.countByPost(post), commentRepository.countByPost(post), likeRepository.existsByUserAndPost(user, post)))
                .collect(Collectors.toList());

        return new ProfileResponse(user.getUsername(), postResponses);
    }
}
