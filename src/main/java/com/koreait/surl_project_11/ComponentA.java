package com.koreait.surl_project_11;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 컴포넌트화 되어야 Autowired 해서 쓸 수 있다.
@Component
@RequiredArgsConstructor    //
public class ComponentA {

    // final이 붙고, RequiredArgsConstructor 가 있으면 @Autowired가 필요하지 않다.
    // 요즘엔 이렇게 쓰는 것을 선호함
    // 알아서 final 객체가 연결이 된다.
    private final ComponentB componentB;

//    @Autowired  // 자동으로 객체 연결 => new 를 하지 않음
//    private ComponentB componentB;
    @Autowired
    private ComponentC componentC;
    @Autowired
    private ComponentD componentD;
    @Autowired
    private ComponentE componentE;
    // CDE의 생성자에 ~컴포넌트 생성됨을 써두면 Autowired 될 때 실행된다.

    public String action() {
        return "ComponentA action /" + componentB.getAction();
    }
}

// 어노테이션을 붙이는 목적?
// 스프링부트한테 얘는 이런거야 하고 하려고.
// 이 클래스는 이런 목적으로 만들어졌어.. 라고 알려주는것.
// 사람한테는 주석처럼, 스프링부트한테는 어노테이션을 붙여줘야한다.