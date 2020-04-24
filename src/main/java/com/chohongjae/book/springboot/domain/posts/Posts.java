package com.chohongjae.book.springboot.domain.posts;


import com.chohongjae.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복의 어노테이션
@NoArgsConstructor // 롬복의 어노테이션, 기본 생성자 자동 추가, 인자가없는, null로 초기화
@Entity // jpa의 어노테이션, 테이블과 링크될 클래스임을 나타낸다.
// 실제 DB의 테이블과 매칭될 클래스, Entity 클래스라고도 한다.
// Entity 클래스에서는 절대 setter 메소드를 만들지 않는다!
// 값 변경이 필요할 시 setter 대신 그 목적과 의대롤 나타낼 수 있는 메소드를 추가한다.
public class Posts extends BaseTimeEntity {

    @Id // 테이블의 pk 필드를 나타낸다.
    // pk의 생성 규칙, GenerationType.IDENTITY 이 옵션이 있어야 auto_increment가 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 되된다.
    // 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
    // 문자열의 경우 varchar(255)가 기본값인데, 사이즈를 늘리거나 타입을 변경하고싶은경우에 사용된다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    // 빌더 패턴 클래스를 왜 쓰는지 이해하자.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        /* 데이터베이스에 쿼리를 날리는 부분이 없는 이유는 jpa의 영속성 컨텍스트떄문이다.
        영속성 컨텍스트란 엔티리를 영구저장하는 환경이다. jpa의 핵심내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐, 아니냐로 갈린다.
        jpa의 엔티티 매니저가 활성화된 상태(spring data jpa를 쓴다면 기본 옵션)로 트랜잭션 안에서
        데이터베이스에서 데이터를 가져오면 그 데이터는 영속성 컨텍스트가 유지된 상태이다.
        이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에서 해당 테이블에 변경분을 "반영한다".
        즉 entity 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없는 것이다.
        이러한 개념을 더티 채킹이라고 한다.
         */
        this.title = title;
        this.content = content;
    }
}

// 도메인이란 게시글, 댓글, 회원, 정산, 결제 등 소프트웨어 문제 영역이다.
