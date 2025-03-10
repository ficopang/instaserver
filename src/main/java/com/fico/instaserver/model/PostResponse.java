package com.fico.instaserver.model;

import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private String imageUrl;
    private String caption;
    private String username;
    private LocalDateTime createdAt;
    private long likeCount;
    private long commentCount;
    private boolean liked;

    public PostResponse(Post post, User user, long likeCount, long commentCount, boolean liked) {
        this.id = post.getId();
        this.imageUrl = post.getImageUrl();
        this.caption = post.getCaption();
        this.username = user.getUsername();
        this.createdAt = post.getCreatedAt();
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.liked = liked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }
}