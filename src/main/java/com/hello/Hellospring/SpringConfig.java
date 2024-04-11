package com.hello.Hellospring;

import com.hello.Hellospring.aop.TimeTraceAop;
import com.hello.Hellospring.repository.JdbcMemberRepository;
import com.hello.Hellospring.repository.JdbcTemplateMemberRepository;
import com.hello.Hellospring.repository.JpaMemberRepository;
import com.hello.Hellospring.repository.MemberRepository;
import com.hello.Hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() { //이 로직을 호출해서 스프링빈에 등록해줌
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
    */
/*
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JdbcMemberRepository(dataSource);
    }*/
}
