package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {

    @Aspect
    @Order(1)
    public static class LogAspect{
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {   //dolog라는 어드바이스
            log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
            return joinPoint.proceed(); //methodInvocation.proceed 호출
        }
    }

    @Aspect
    @Order(2)
    public static class TxAspect{
        @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try{
                log.info("[transaction 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[transaction 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e){
                log.info("[transaction 롤백] {}", joinPoint.getSignature());
                throw e;
            }finally {
                log.info("[resource realese] {}", joinPoint.getSignature());
            }
        }
    }


}
