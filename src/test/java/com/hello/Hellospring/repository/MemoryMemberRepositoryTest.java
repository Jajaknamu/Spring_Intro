package com.hello.Hellospring.repository;

import com.hello.Hellospring.domain.Member;
import com.hello.Hellospring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    //MemberService memberService = new MemberService();

    @AfterEach //이 메소드가 실행 끝날때 마다 어떠한 동작을함(콜백 메소드)
    public void afterEach() {
        repository.clearStore(); //테스트가 실행되고 끝날때마다 한번씩 저장소 다 지움
    }

    @Test
    public void save(){//멤버저장 잘 되는지 테스트
        Member member = new Member();
        member.setName("지은"); //이름 세팅해줌

        repository.save(member); // 멤버를 리포지토리에 저장 해봄

        //검증을 해보는거임 ,name을 저장하면 자동으로 id가 세팅되는 원리
        //뒤에 optional에서 get으로 바로 꺼내보는게 좋은건 아님 걍 예제니까
        Member result = repository.findById(member.getId()).get();
        //저장한 new Member에서 한거랑 db에서 꺼낸거랑 같으면 메모리에 저장하기 때문에 참이됨
        // -> 실행시 true 나옴
        //System.out.println("result = " + (result==member)); -> 텍스트로 계속 보는건 안좋음

        //junit에서 제공한 aseert
        Assertions.assertEquals(member, result);
                               //기대값  //실제값
        //assertj에서 제공한 assert
        // -> 앞에 생략하고 싶으면 import assertj 해주면 바로 assertThat 사용가능
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);

    }
    //테스트가 끝날때 마다 저장소를 다 지워줘야함
    //findAll 추가하면서 clean 안해주서 오류남
    // -> findAll에서 이미 저장해버려서 다른 객체에서 값이 나와버리니까 에러남
    //모든 테스트 순서랑 상관없이 메소드별로 다 따로 동작하게 설계해야함 -> 순서에 의존 금지
    @Test
    public void findByName() { //name으로 회원정보 찾는 테스트
        Member member1 = new Member();
        member1.setName("스프링1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("스프링2");
        repository.save(member2);

        Member result = repository.findByName("스프링1").get();//optional 까고 확인하는 거임

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("스프링1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("스프링2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
