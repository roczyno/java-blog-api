package com.roczyno.blog.api.service;

import com.roczyno.blog.api.entity.Post;
import com.roczyno.blog.api.payload.PostDto;
import com.roczyno.blog.api.payload.PostResponse;
import com.roczyno.blog.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert Dto to entity
        Post post= mapToEntity(postDto);

        Post newPost= postRepository.save(post);
        //convert entity to Dto
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize) {
        // create pagination

        Page<Post> posts= postRepository.findAll(PageRequest.of(pageNo,pageSize));
        //get content from page object
        List<Post> listOfPosts = posts.getContent();
       List<PostDto> content = listOfPosts.stream().map(this::mapToDto).collect(Collectors.toList());
        PostResponse postResponse= new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;


    }

    @Override
    public PostDto getPostById(Long id) {
        Post post= postRepository.findById(id).orElseThrow();
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        //get post by id from the database
        Post post= postRepository.findById(id).orElseThrow();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post= postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }

    //converted entity to dto
    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());



        return postDto;
    }

    //convert Dto to entity
    private Post mapToEntity(PostDto postDto){
        Post post= new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }

}
