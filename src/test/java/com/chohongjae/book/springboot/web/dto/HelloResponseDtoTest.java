package com.chohongjae.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void testLomBok() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        System.out.println(dto.getAmount());
        //then
        // assertj라는 테스트 검증 라이브러리의 검증 메소드이다.
        // 검증하고 싶은 대상을 메소드 인자로 받는다.
        // 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.
        assertThat(dto.getName()).isEqualTo(name); // assertj의 동등 비교 메소드
        assertThat(dto.getAmount()).isEqualTo(amount);


    }
}