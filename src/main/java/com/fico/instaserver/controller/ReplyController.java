package com.fico.instaserver.controller;

import com.fico.instaserver.model.Reply;
import com.fico.instaserver.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/{commentId}")
    public ResponseEntity<Reply> addReply(@PathVariable Long commentId, @RequestParam String content,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(replyService.addReply(userDetails.getUsername(), commentId, content));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<List<Reply>> getReplies(@PathVariable Long commentId) {
        return ResponseEntity.ok(replyService.getRepliesByComment(commentId));
    }
}