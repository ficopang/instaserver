package com.fico.instaserver.model;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResponse {
    private Long id;
    private String username;
    private String content;

    public ReplyResponse(Long id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
