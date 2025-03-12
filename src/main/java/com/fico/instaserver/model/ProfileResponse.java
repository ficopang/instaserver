package com.fico.instaserver.model;

import java.util.List;

public class ProfileResponse {
    String username;
    List<PostResponse> posts;

    public ProfileResponse(String username, List<PostResponse> posts) {
        this.username = username;
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }
}
