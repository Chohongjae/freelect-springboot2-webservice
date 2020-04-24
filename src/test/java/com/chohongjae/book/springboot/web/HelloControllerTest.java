package com.chohongjae.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트를 진행할 때 JUnit에 내장된 실행자 되에 다른 실행자를 실행시킨다
// 여기서는 SpringRunner라는 스프링 실행자를 사용한다.
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
// 여러 스프링 테스트 어노테이션 중 web(spring mvc)에 집중할 수 있는 어노테이션이다.
// 선언할경우 @Controlloer, @ControllerAdvice 등을 사용할 수 있지만
// @Service, @Component, @Repository는 사용할 수 없다.
// 여기서는 컨트롤러만 사용하기 때문에 사용
public class HelloControllerTest {

    // 스프링이 관리하는 Bean을 주입 받는다.
    // 보통 bean 주입은 autowired, setter, 생성자로 가능한데, 생성자를 사용하는게 바람직하다.
    @Autowired
    // 웹 api를 테스트할 때 사용한다.
    // 스프링 mvc 테스트의 시작점이다.
    // 이 클래스를 통해 http method에 대한 api 테스트를 할 수 있다.
    private MockMvc mvc;

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        //MockMvc를 통해 /hello 주소로 get 요청을 한다. 체이닝이 지원된다.
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
        // jsonPath는 json 응답값을 필드별로 검증할 수 있는 메소드이다.
        // $를 기준으로 필드명을 명시한다.
    }
}
