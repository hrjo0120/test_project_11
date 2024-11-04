package com.koreait.surl_project_11;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("a")
    @ResponseBody
    public String hi1(
            String age,
            String id
    ) {
        return "안녕, %s번, %s살이야.".formatted(id, age);
    }

    @GetMapping("b")
    @ResponseBody
    public String plus(
            @RequestParam("a") String numstr,
            String b
    ) {     //http://localhost:8080/b?a=1&b=2
        int num1 = Integer.parseInt(numstr);
        int num2 = Integer.parseInt(b);

        System.out.println("a : " + num1);
        System.out.println("b : " + num2);
        return "a + b = %s".formatted(num1 + num2);
    }
}

/*
- HTTP는 편지를 이용해서 고객(==브라우저)과 점원(==서버의 컨트롤러)이 대화를 나누는 방법에 대한 규칙이 정해져있다.
- 고객 == 클라이언트 == 브라우저 == 서비스 이용자
- 점원(주인) == 서버 == 스프링부트 == 서비스 제공자
- 고객은 요청 편지를 전원에게 보내고, 점원은 그에 대한 응답 편지를 보낸다.
- 편지는 header와 body로 나뉜다.
- 헤더에는 부가정보, 바디에는 내용이 담긴다.
- 고객의 생각(데이터)이 점원에게 전해지려면, 양쪽이 모두 다 이해할 수 있는 String을 사용해야 한다.
- 왜? 브라우저(고객)의 모국어는 자바스크립트, 스프링부트(점원)의 모국어는 자바
  - 따라서 브라우저는 자바의 int, boolean, char, 배열, 리스트, 맵 ... 을 이해할 수 없다.
  - 마찬가지로 스프링부트도 브라우저 측의 number, 객체 ... 를 이해할 수 없다.
  - 동시에 이해하는 게 String 이다. => 둘이 소통하려면 String을 기본으로 사용해야 한다.
  - String == 공용어 느낌
 - String만으로 객체를 표현하는 데 어려움이 있어서 JSON을 사용함
*/