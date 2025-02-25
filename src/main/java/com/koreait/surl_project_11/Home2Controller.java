package com.koreait.surl_project_11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home2")   // 접두사 처럼 됨
public class Home2Controller {

    @Autowired
    private ComponentA componentA;

//    public Home2Controller() {
//        componentA = new ComponentA();
//    }

    //http://localhost:8080/home2/action1
    @GetMapping("/action1")
    @ResponseBody
    public String action() {
        return componentA.action();
    }
}
