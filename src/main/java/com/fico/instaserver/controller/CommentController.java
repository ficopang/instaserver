package com.fico.instaserver.controller;

import com.fico.instaserver.model.Comment;
import com.fico.instaserver.model.CommentResponse;
import com.fico.instaserver.model.PostResponse;
import com.fico.instaserver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,
                                              @RequestParam String text,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(commentService.addComment(userDetails.getUsername(), postId, text));
    }
}
