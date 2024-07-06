package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

    @Around("execution(* hello.aop.order..*(..))")  //포인트컷: join point를 정해줌 이 위치의 모든 클래스를 프록시로 만들어준다.
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {   //dolog라는 어드바이스
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed(); //methodInvocation.proceed 호출
    }
}

/**
 * 1. 스프링 aop는 리플랙션을 이용하여 @Around("execution(* hello.aop.order..*(..))")이 join point의 모든 프록시를 만든다.
 * 2. 그 후 그 정보들을 ProceedingJoinPoint를 구현한 methodInvokation에 넘겨주며 초기화
 * 3. joinPoint는 프록시, 타겟, args 등의 모든 정보를 갖고있음(joinPoint == methodInvokation)
 * 4. proceed를 함으로 타겟의 메서드를 실행 result는 또다른 어드바이스에 넘겨주며 메서드 체인 패턴으로 return result
 */
