package com.fico.instaserver.service;

import com.fico.instaserver.model.*;
import com.fico.instaserver.repository.CommentRepository;
import com.fico.instaserver.repository.PostRepository;
import com.fico.instaserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<CommentResponse> getComments(Long postId) {
        List<Comment> posts = commentRepository.findByPostId(postId);

        return posts.stream()
                .map(comment -> new CommentResponse(comment.getId(), comment.getPost().getId().toString(), comment.getUser().getUsername(), comment.getText(), comment.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public Comment addComment(String username, Long postId, String text) {
        // Retrieve User
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setText(text);

        return commentRepository.save(comment);
    }
}
