package com.roczyno.blog.api.controller;

import com.roczyno.blog.api.payload.PostDto;
import com.roczyno.blog.api.payload.PostResponse;
import com.roczyno.blog.api.service.PostService;
import com.roczyno.blog.api.utils.AppConst;
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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public PostResponse getAllPosts(@RequestParam(name = "pageNo", defaultValue = AppConst.DEFAULT_PAGE_NO, required = false) int pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = AppConst.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(name="sortBy",defaultValue = AppConst.DEFAULT_SORT_BY,required = false) String sortBy,
                                    @RequestParam(name = "sortDir",defaultValue =AppConst.DEFAULT_SORT_DIRECTION,required = false) String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id) {
        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);

    }
}
