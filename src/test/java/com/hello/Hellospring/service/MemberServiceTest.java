package com.hello.Hellospring.service;

import com.hello.Hellospring.domain.Member;
import com.hello.Hellospring.repository.MemberRepository;
import com.hello.Hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*; //자동으로 테스트코드 만들면 생김

public class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    //테스트 할때마다 각각 생성,
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //테스트는 한글로 바꿔도 됨
    @Test
    void 회원가입() {
        //given : 뭔가가 주어짐
        Member member = new Member();
        member.setName("hello");

        //when : 이걸 실행 했을때
        Long saveId = memberService.join(member); //저장한 id 나오게끔 함

        //them : 결과가 이렇게 나옴(검증)
        Member findMember =  memberService.findOne(saveId).get(); // 결과 나오는거 바로 get으로 받기
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}