package com.chohongjae.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
이 어노테이션으로 인해 스프링 부트의 자동설정, 스프링 bean 읽기와 생성을 모두 자동으로 설정된다. 또한 이 어노테이션이
위치한 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 한다.
 */
@EnableJpaAuditing // jpa auditing 어노테이션들을 모두 활성화시키는 어노테이션
@SpringBootApplication
public class Application {
    // 앞으로 만들 프로젝트의 메인 클래스가 된다.
    public static void main(String[] args) {
        // 이 메소드로 내장 was가 실행된다.
        SpringApplication.run(Application.class, args);
    }
}
