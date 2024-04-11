package com.hello.Hellospring.controller;

public class MemberForm {
    private String name;

    //꺼낼때는 getName 사용
    public String getName() {
        return name;
    }
    //html에서 작성된 폼안에 값이 setName으로 들어감
    public void setName(String name) {
        this.name = name;
    }
}
