package com.roczyno.blog.api.service;

import com.roczyno.blog.api.entity.Post;
import com.roczyno.blog.api.payload.PostDto;
import com.roczyno.blog.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert Dto to entity
        Post post= new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post newPost= postRepository.save(post);
        //convert entity to Dto
        PostDto postResponse = new PostDto();
        postResponse.setTitle(newPost.getTitle());
        postResponse.setContent(newPost.getContent());
        postResponse.setDescription(newPost.getDescription());
        return postResponse;
    }
}
