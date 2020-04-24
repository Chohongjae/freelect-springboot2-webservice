package com.chohongjae.book.springboot.web;

import com.chohongjae.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 해당 컨트롤러를 json을 반환하는 컨트롤러로 만들어준다.
// 예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에사용할 수 있게 해준다고 생각하면 된다.
@RestController
public class HelloController {
    @GetMapping("/hello") // Get 요청을 받을 수 있는 API를 만들어준다.
    public String hello() {
        return "hello";  // 이제 /hello로 요청이오면 문자열 hello를 반환하는 기능을 가지게 되었다.
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        // RequestParam 은 외부에서 api로 넘긴 파라미터를 가져오는 어노테이션이다.
        // 여기서는 외부에서 name이란 이름으로 넘긴 쿼리 파라미터를 메소드 파라미터에 저장하게 된다.
        return new HelloResponseDto(name, amount);
    }
}
