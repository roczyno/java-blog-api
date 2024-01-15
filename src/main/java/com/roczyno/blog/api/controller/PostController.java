package com.roczyno.blog.api.controller;

import com.roczyno.blog.api.payload.PostDto;
import com.roczyno.blog.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
   private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }
}
