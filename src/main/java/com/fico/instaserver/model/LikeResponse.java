package com.fico.instaserver.model;

public class LikeResponse {
    String username;
    PostResponse post;

    public LikeResponse(String username, PostResponse post) {
        this.username = username;
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PostResponse getPost() {
        return post;
    }

    public void setPost(PostResponse post) {
        this.post = post;
    }
}
