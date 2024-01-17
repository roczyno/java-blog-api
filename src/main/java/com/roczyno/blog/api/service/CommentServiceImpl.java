package com.roczyno.blog.api.service;

import com.roczyno.blog.api.entity.Comment;
import com.roczyno.blog.api.entity.Post;
import com.roczyno.blog.api.payload.CommentDto;
import com.roczyno.blog.api.repository.CommentRepository;
import com.roczyno.blog.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

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

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }


    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        return comment;
    }
}
