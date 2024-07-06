package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
@Slf4j
@Aspect
public class AspectV2 {
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){}   //pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {   //dolog라는 어드바이스
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed(); //methodInvocation.proceed 호출
    }


}
