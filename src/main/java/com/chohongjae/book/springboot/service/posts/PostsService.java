package com.chohongjae.book.springboot.service.posts;

import com.chohongjae.book.springboot.domain.posts.Posts;
import com.chohongjae.book.springboot.domain.posts.PostsRepository;
import com.chohongjae.book.springboot.web.dto.PostsListResponseDto;
import com.chohongjae.book.springboot.web.dto.PostsResponseDto;
import com.chohongjae.book.springboot.web.dto.PostsSaveRequestDto;
import com.chohongjae.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service // 일반적으로 controller와 dao의 중간영역에서 사용된다. 비즈니스 로직은 서비스 영역이아니라 도메인영역에서 처리되어야한다! 서비스 메소드는 트랜잭션과 도메인간의 순서만 보장해준다.
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId(); // 결국에 레포지토리로 왔다갔다하는건 엔티티인것같다!
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // readonly를 추가하면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다. 등록, 수정, 삭제등이 없을때 사용하자.
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(posts -> new PostsListResponseDto(posts)).collect(Collectors.toList());
    }
}

/*
    스프링에서 bean을 주입받는 방식
    1) @Autowired (권장하지 않음)
    2) setter
    3) 생성자 (가장 권장)
 */