package com.horizon.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    @Pointcut("execution(* com.horizon.service.impl.*.*(..))")
    public void pointCut() {
    }

    @Around(value="pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.err.println(pjp.getSignature().getName() + "耗时：" + (end - start) + "ms.");
        // 返回连接点方法的返回值
        return result;
    }

}
