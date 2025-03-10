package com.fico.instaserver;

import com.fico.instaserver.model.Comment;
import com.fico.instaserver.model.Like;
import com.fico.instaserver.model.Post;
import com.fico.instaserver.model.User;
import com.fico.instaserver.repository.CommentRepository;
import com.fico.instaserver.repository.LikeRepository;
import com.fico.instaserver.repository.PostRepository;
import com.fico.instaserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) {
            User user1 = new User("user1", passwordEncoder.encode("asd"));
            userRepository.save(user1);
            User user2 = new User("user2", passwordEncoder.encode("asd"));
            userRepository.save(user2);

            Post postByUser1 = new Post("/uploads/38e5fff4-a211-44eb-ab9f-3eaa876d08ac_IMG_20250308_164904.jpg", "This is a long caption so we can test for expandable", user1);
            postRepository.save(postByUser1);

            likeRepository.save(new Like(user1, postByUser1));
            likeRepository.save(new Like(user2, postByUser1));

            commentRepository.save(new Comment("Good Post", user2, postByUser1));
            commentRepository.save(new Comment("Thanks!", user1, postByUser1));

            System.out.println("Dummy data inserted!");
        } else {
            System.out.println("Data already exists. Skipping insertion.");
        }
    }
}
