package com.roczyno.blog.api.service;

import com.roczyno.blog.api.payload.PostDto;
import org.springframework.http.HttpStatusCode;

public interface PostService {

    PostDto createPost(PostDto postDto);
}
