package com.chohongjae.book.springboot.web;


import com.chohongjae.book.springboot.service.posts.PostsService;
import com.chohongjae.book.springboot.web.dto.PostsResponseDto;
import com.chohongjae.book.springboot.web.dto.PostsSaveRequestDto;
import com.chohongjae.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor  // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해준다. 그래서 postsService를 사용할 수 있는것!
@RestController // 해당 컨트롤러를 json을 반환하는 컨트롤러로 만들어준다.
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
