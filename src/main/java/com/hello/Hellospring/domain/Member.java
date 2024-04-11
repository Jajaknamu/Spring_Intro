
package com.hello.Hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //jpa가 관리하는 엔티티가 됨
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//pk를 맵핑해줘야함.
    private Long id; //임의의 값 -> 시스템이 저장하는 값임
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
