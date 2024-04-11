package com.hello.Hellospring.repository;

import com.hello.Hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//이 코드들이 구현체라 함
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0,1,2 키 값 생성
    //실무에선 동시선 문제를 고려해서 atomic long 권장 -> 예제라 단순화
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //스토어에 넣기 전에 멤버에 id값 세팅 해주고
        store.put(member.getId(), member); // 세팅된 id를 스토어에 저장해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //null값이 들어오면 안되지만 혹시 들어오면 감싸서 반환해줌 -> 그래야 클라이언트에서 뭘 할 수 있음
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                //람다식 사용해서 파라미터로 넘어온 name 값이 같은지 확인, 같은 경우 필터링 됨
                .findAny(); //그 중에서 그냥 찾으면 optional에 감싸서 반환됨
        // 루프를 다 돌면서 그냥 하나를 찾아주면 걔를 그냥 반환해버림
        // 근데 끝까지 돌았는데도 없으면 null이 포함되서 반환
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 스토어에 있는 values가 멤버임.
        // 이러면 멤버들이 다 반환됨
        //실무에선 list 많이씀 -> 루프돌리기 편해서
    }
    public void clearStore() {
        store.clear(); //
    }
    //이렇게 구현한걸 검증해보려면 테스트 케이스 작성하면됨
}
