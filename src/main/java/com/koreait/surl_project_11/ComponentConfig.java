package com.koreait.surl_project_11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfig {

    // 등록만 된 메소드, 언제 실행할지는 정해지지 않음
    // 컴포넌트와 동일한 역할을 하게됨
    @Bean
    public ComponentC componentC() {
        return new ComponentC();
    }

    @Bean   // 등록
    public ComponentD componentD() {
        return new ComponentD();
    }

    @Bean
    public ComponentE componentE() {
        return new ComponentE();
    }
}
