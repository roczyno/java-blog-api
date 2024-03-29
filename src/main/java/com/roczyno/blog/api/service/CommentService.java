package com.roczyno.blog.api.service;

import com.roczyno.blog.api.payload.CommentDto;

import java.util.List;

public interface CommentService {
    

    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateComment(CommentDto commentDto, long postId, long commentId);

    void deleteComment(long postId, long commentId);
}
