package com.simpleboard.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private final List<Post> posts = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(posts);
    }
}
