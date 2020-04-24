package com.chohongjae.book.springboot.web.domain.posts;


import com.chohongjae.book.springboot.domain.posts.Posts;
import com.chohongjae.book.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // Junit에서 단위 테스트가 끝날 때 마다 수행되는 메소드를 지정
    // 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기위해 사용한다
    // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음 테스트를 실행 시 테스트가 실패할 수 있다.
    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void loadPost() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 테이블 posts에 insert/update 쿼리를 실행한다.
        // id 값이 있다면 update가, 없다면 insert 쿼리가 실행된다.
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("bjq913@gmail.com")
                .build());

        // when
        // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드이다.
        List<Posts> postsList = postsRepository.findAll(); //필요한 메소드가 있으면 repository에 선언하면 된다. 인터페이스 메소드로
        System.out.println(postsList.get(0));
        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void postBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println("createdDate="+posts.getCreatedDate());
        System.out.println("modifedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
