package com.fico.instaserver.service;

import com.fico.instaserver.model.Comment;
import com.fico.instaserver.model.Reply;
import com.fico.instaserver.model.User;
import com.fico.instaserver.repository.CommentRepository;
import com.fico.instaserver.repository.ReplyRepository;
import com.fico.instaserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Reply addReply(String username, Long commentId, String content) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        Reply reply = new Reply();
        reply.setUser(user);
        reply.setContent(content);
        reply.setComment(comment);

        return replyRepository.save(reply);
    }

    public List<Reply> getRepliesByComment(Long commentId) {
        return replyRepository.findByCommentId(commentId);
    }
}