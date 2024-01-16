package com.roczyno.blog.api.service;

import com.roczyno.blog.api.payload.PostDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);


    List<PostDto> getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePost(Long id);
}
