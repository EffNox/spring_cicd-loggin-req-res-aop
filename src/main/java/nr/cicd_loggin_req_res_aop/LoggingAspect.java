package nr.cicd_loggin_req_res_aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* nr.cicd_loggin_req_res_aop..*(..))")
    public void applicationMethods() {}

    @Before("applicationMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: {}", joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("REQ: {}", arg);
        }
    }

    @Around("applicationMethods()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("Method execution start: {}", point.getSignature());
        Object returnValue;
        try {
            returnValue = point.proceed();
        } catch (Exception e) {
            log.error("Exception in method: {}", point.getSignature(), e);
            throw e;
        }
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Time taken: {} is {} ms ", point.getSignature(), timeTaken);

        return returnValue;
    }

    @AfterReturning(pointcut = "applicationMethods()", returning = "result")
    public void logAfterRunning(JoinPoint point, Object result) {
        log.info("Exiting method: {}", point.getSignature());
        log.info("RES: {}", result);

    }

    @AfterThrowing(pointcut = "applicationMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint point, Throwable ex) {
        log.error("Exception in method: {}", point.getSignature(), ex);
    }

}
