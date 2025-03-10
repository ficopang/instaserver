package com.fico.instaserver.model;

import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String postId;
    private String username;
    private String text;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String postId, String username, String text, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.username = username;
        this.text = text;
        this.createdAt = createdAt;
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
}
