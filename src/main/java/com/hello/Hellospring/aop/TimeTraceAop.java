package com.hello.Hellospring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Aspect //aop 사용시 이 어노테이션 사용
@Component //스프링빈으로 등록해주는 어노테이션 -> 원래는 수동으로 빈 등록해주는게 맞음
public class TimeTraceAop {
    //패키지 하위에 다 적용해준다는 의미의 어노테이션
    @Around("execution(* com.hello.Hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+ timeMs + "ms");
        }
    }
}
