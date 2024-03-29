package com.roczyno.blog.api.service;

import com.roczyno.blog.api.entity.Comment;
import com.roczyno.blog.api.entity.Post;
import com.roczyno.blog.api.exception.BlogAPIException;
import com.roczyno.blog.api.exception.ResourceNotFoundException;
import com.roczyno.blog.api.payload.CommentDto;
import com.roczyno.blog.api.repository.CommentRepository;
import com.roczyno.blog.api.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        //return post by entity id
        Post post = postRepository.findById(postId).orElseThrow();
        //set post to comment
        comment.setPost(post);
        //save comment to DB
        commentRepository.save(comment);
        return mapToDto(comment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments= commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","id",postId));
        Comment comment= commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment not found");
        }
        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","id",postId));
        Comment comment= commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment not found");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","id",postId));
        Comment comment= commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment not found");
        }
        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment) {
       return modelMapper.map(comment, CommentDto.class);
    }


    private Comment mapToEntity(CommentDto commentDto) {
       return modelMapper.map(commentDto,Comment.class);
    }
}
