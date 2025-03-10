package com.fico.instaserver.controller;

import com.fico.instaserver.model.Post;
import com.fico.instaserver.model.PostResponse;
import com.fico.instaserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(postService.getAllPosts(userDetails.getUsername()));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam("caption") String caption,
                                           @RequestParam("image") MultipartFile image,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(postService.createPost(caption, image, userDetails.getUsername()));
    }

}
