package com.chohongjae.book.springboot.web.dto;

import com.chohongjae.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto { // 계층간에 데이터 교환을 위한 객체

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
