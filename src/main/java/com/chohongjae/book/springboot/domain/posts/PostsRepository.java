package com.chohongjae.book.springboot.domain.posts;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Posts 클래스로 database를 접근하게 해줄 클래스
// db layer 접근자로서 jpa에선 repository라고 부르며 인터페이스로 생성한다.
// 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK타입> 을 상속하면 기본적인 crud 메소드가 자동으로 생성된다.
// Entity 클래스와 Entity repository는 함께 위치해야 한다.
public interface PostsRepository extends JpaRepository<Posts, Long> { // 데이터 저장소에 접근하는 영역, dao 영역
    // spring data jpa에서 제공하지 않는 메소드는 아래처럼 쿼리로 @query를 사용하여 작성해도 된다.
    // 물론 아래 메소드는 지원한다.
    // 규모가 있는 프로젝트에서의 데이터 조회는 fk의 조인, 복잡한 조건 등으로 인해 이런 entity 클래스만으로 처리하기 어려워
    // 조회용 프레임워크(querydsl, jooq, mybatis)등을 추가로 사용한다.
    // 조회는 위 3가지 프레임워크 중 하나를 통해 조회하고 등록/수정/삭제등은 spring data jpa를 통해 진행한다.
    // querydsl 추천 (타입 안전성, 많은 레퍼런스 보유 등)
    @Query("SELECT p From Posts p order by p.id DESC")
    List<Posts> findAllDesc();

    // 이런식으로 내가 메소드를 정의할 수 있다.
//    List<Comment> findAllByPost(Post post);
}


// jpa는 인터페이스로서 자바 표준명세서이다. 인터페이스인 jpa를 사용하기위해서는 구현체가 필요하다.
// 구현체의 종류로는 hibernate, eclipse link 등이 있는데 이러한 구현체들을 좀 더 쉽게 사용하고자
// 추상화시킨 spring data jpa라는 모듈을 이용하여 jpa를 다룬다.

// jpa <- hibernate <- spring data jpa 가 이용된다.