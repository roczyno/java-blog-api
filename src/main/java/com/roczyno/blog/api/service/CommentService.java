package com.roczyno.blog.api.service;

import com.roczyno.blog.api.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
