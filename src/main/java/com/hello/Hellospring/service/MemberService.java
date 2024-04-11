package com.hello.Hellospring.service;

import com.hello.Hellospring.domain.Member;
import com.hello.Hellospring.repository.MemberRepository;
import com.hello.Hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public class MemberService {

    //테스트에서 만든 리포지토리랑 이 리포지토리랑 다름. 즉 다른 인스턴스임
    // -> MemoryMemberRepository에서 static이 아닐경우 문제생김: 다른 db에 저장되는 문제
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) { //외부에서 넣어주기 식.
        // 멤버 서비스를 멤버 리포지토리를 직접 내가 new 해서 생성하는게 아니라 외부에서 넣어주도록 바꿈
        this.memberRepository = memberRepository;
    }

    /* 회원가입 */
    public Long join(Member member) {

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start; //총 걸린 시간
            System.out.println("join = " + timeMs + "ms");
        }


        /*
        //중복 이름 회원x
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //null이 아닌 어떤 값이 있으면 이 로직이 동작함
            // -> optional이기 때문에 가능
            throw new IllegalStateException("이미 있는 회원 입니다.");
        });
        memberRepository.save(member);
        return member.getId();*/
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {

        long strar = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - strar;
            System.out.println("findMembers = " + timeMs + "ms");
        }

    }

    public Optional<Member> findOne(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
