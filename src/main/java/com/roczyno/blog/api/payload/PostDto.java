package com.roczyno.blog.api.payload;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String Content;
}
