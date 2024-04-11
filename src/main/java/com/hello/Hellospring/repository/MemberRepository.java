package com.hello.Hellospring.repository;

import com.hello.Hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository { //4가지 기능 추가
    Member save(Member member); //회원이 저장소에 저장
    Optional<Member> findById(Long id); //저장소에서 id로 회원정보 찾아올수있음
    Optional<Member> findByName(String name); //저장소에서 name으로 회원정보 찾아올수있음
    List<Member> findAll(); //지금까지 저장된 모든 회원 리스트 반환


}
