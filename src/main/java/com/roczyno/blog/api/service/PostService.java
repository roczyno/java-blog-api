package com.roczyno.blog.api.service;

import com.roczyno.blog.api.payload.PostDto;
import com.roczyno.blog.api.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);


 PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePost(Long id);
}
