package com.fico.instaserver.service;

import com.fico.instaserver.model.Post;
import com.fico.instaserver.model.PostResponse;
import com.fico.instaserver.model.User;
import com.fico.instaserver.repository.CommentRepository;
import com.fico.instaserver.repository.LikeRepository;
import com.fico.instaserver.repository.PostRepository;
import com.fico.instaserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    private static final String UPLOAD_DIR = "uploads/";

    public List<PostResponse> getAllPosts(String username) {
        User user = userService.getUserByUsername(username);
        List<Post> posts = new ArrayList<>();

        if(user.getRole().equals("admin")) {
            posts = postRepository.findAllByOrderByIdDesc();
        } else {
            posts = postRepository.findAllByUser(user);
        }

        return posts.stream()
                .map(post -> new PostResponse(post, post.getUser(), likeRepository.countByPost(post), commentRepository.countByPost(post), likeRepository.existsByUserAndPost(user, post)))
                .collect(Collectors.toList());
    }

    public Post createPost(String caption, MultipartFile image, String username) {
        try {
            // Save image to server
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getBytes());

            // Retrieve User
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create and save Post
            Post post = new Post();
            post.setCaption(caption);
            post.setImageUrl("/uploads/" + fileName);
            post.setUser(user);

            return postRepository.save(post);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }
}

