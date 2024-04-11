package com.hello.Hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //로컬호스트 8080으로 들어오면 이게 호출된다는 의미
    public String home() {
        return "home"; //home.html을 호출하는거
    }
}
