package com.fico.instaserver.model;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponse {
    private Long id;
    private String postId;
    private String username;
    private String text;
    private List<ReplyResponse> replies;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String postId, String username, String text, List<ReplyResponse> replies, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.username = username;
        this.text = text;
        this.createdAt = createdAt;
        this.replies = replies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ReplyResponse> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyResponse> replies) {
        this.replies = replies;
    }
}
