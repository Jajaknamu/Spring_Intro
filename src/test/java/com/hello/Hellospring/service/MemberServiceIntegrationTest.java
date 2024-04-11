package com.hello.Hellospring.service;

import com.hello.Hellospring.domain.Member;
import com.hello.Hellospring.repository.MemberRepository;
import com.hello.Hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest { //통합테스트

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    //테스트는 한글로 바꿔도 됨
    @Test
    void 회원가입() {
        //given : 뭔가가 주어짐
        Member member = new Member();
        member.setName("spring100");

        //when : 이걸 실행 했을때
        Long saveId = memberService.join(member); //저장한 id 나오게끔 함

        //them : 결과가 이렇게 나옴(검증)
        Member findMember =  memberService.findOne(saveId).get(); // 결과 나오는거 바로 get으로 받기
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}