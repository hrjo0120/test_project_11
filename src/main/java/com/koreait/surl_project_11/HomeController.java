package com.koreait.surl_project_11;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            //http://localhost:8080/b?a=20&b=40
            @RequestParam("a") int num1,
            @RequestParam("b") int num2,
            @RequestParam(name = "c", defaultValue = "10") int num3
    ) {     //http://localhost:8080/b?a=1&b=2

        System.out.println("a : " + num1);
        System.out.println("b : " + num2);
        return "a + b + c = %d".formatted(num1 + num2 + num3);
    }

    @GetMapping("c")
    @ResponseBody
    public String c(
            boolean married
    ) {
        return married ? "기혼" : "미혼";
    }

    @GetMapping("d")
    @ResponseBody
    public String d(
            Boolean married     // Boolean : 기본타입의 확장형
    ) {
        if (married == null) return "정보 입력해";
        return married ? "기혼" : "미혼";
    }

    @Data
    @ToString
    @AllArgsConstructor     // 필드가 final 일때 required
    public static class Person {
        private String name;
        private int age;
    }

    @GetMapping("person1")
    @ResponseBody
    public String person1(
            String name,
            int age
    ) {
        Person person = new Person(name, age);
        return person.toString();
    }

    @GetMapping("person2")
    @ResponseBody
    public String person2(
            Person person
    ) {
        return person.toString();
    }

    @GetMapping("e")
    @ResponseBody
    public int e() {
        int age = 10;   // 문자열 10
        return age;
    }

    @GetMapping("f")
    @ResponseBody
    public ArrayList f() {
        ArrayList<int[]> arr = new ArrayList<>();
        arr.add(new int[]{1, 2, 3});
        arr.add(new int[]{2});
        arr.add(new int[]{3});
        return arr;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    @EqualsAndHashCode
    public static class Post {
        @ToString.Exclude   // 이렇게 하게되면 id는 ToString 할 때 빠짐.
        @JsonIgnore     // 이렇게 쓰게되면 GetMapping("/posts") 에서 볼 때도 빠지게 된다.
        @EqualsAndHashCode.Include  // Include 된 필드로만 비교
        private Long id;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        @Builder.Default    // 이렇게 쓰게 되면 subject 가 없는 곳에 기본 값으로 이게 뜨게 됨.
        private String subject = "제목이야";
        private String body;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>() {{//add 나 put 로 넣지 않고 ..
            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목1", "내용1"));
            add(new Post(2L, LocalDateTime.now(), LocalDateTime.now(), "제목2", "내용2"));
            add(new Post(3L, LocalDateTime.now(), LocalDateTime.now(), "제목3", "내용3"));
            add(new Post(4L, LocalDateTime.now(), LocalDateTime.now(), "제목4", "내용4"));
        }};
        return posts;
    }

    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPosts2() {
        List<Post> posts = new ArrayList<>() {{//add 나 put 로 넣지 않고 ..
//            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목1", "내용1"));
            add(Post
                    .builder()  //사용하려면 필드에 어노테이션 붙어있어야함
                    .id(1L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
//                    .subject("제목1")
                    // 기본값으로 "제목이야"를 넣었는데, 값을 까보면 null로 나온다. => default 로 넣는 값에 @Builder.Default 를 붙여줘야 값이 나오게 된다.
                    .body("내용1")
                    .build());
            add(Post
                    .builder()
                    .id(2L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목2")
                    .body("내용2")
                    .build());
            add(Post
                    .builder()
                    .id(3L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목3")
                    .body("내용3")
                    .build());
            add(Post
                    .builder()
                    .id(4L)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .subject("제목4")
                    .body("내용4")
                    .build());
        }};
        return posts;
    }

    @GetMapping("/posts/1")
    @ResponseBody
    public Post getPost() {
        Post post = Post.builder()
                .id(1L)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .body("내용1")
                .build();

        System.out.println(post);
        return post;
    }

    @SneakyThrows
    @GetMapping("/posts/2")
    @ResponseBody
    public Post getPost2() {
        Post post = Post.builder()
                .id(1L)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .body("내용1")
                .build();

        Thread.sleep(2000);     // 2초 동안 일시정지

        System.out.println(post);
        return post;
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