package com.fico.instaserver.controller;

import com.fico.instaserver.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> toggleLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetails userDetails) {
        String response = likeService.toggleLike(postId, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }
}

