package org.demo.spring.proxyselfcall.logaround;


import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;

class LogAroundAnnotationAdvisor extends AbstractPointcutAdvisor {
    private final Advice advice;
    private final Pointcut pointcut;

    public LogAroundAnnotationAdvisor() {
        this.advice = buildAdvice();
        this.pointcut = buildPointcut();
    }

    private Advice buildAdvice() {
        return (MethodInterceptor) invocation -> {
            Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
            Method userMethod = BridgeMethodResolver.getMostSpecificMethod(invocation.getMethod(), targetClass);
            LogAround anno = AnnotatedElementUtils.findMergedAnnotation(userMethod, LogAround.class);

            System.out.println("before " + anno.value());
            var result = invocation.proceed();
            System.out.println("after " + anno.value());
            LogAroundCounter.increment();

            return result;
        };
    }

    private Pointcut buildPointcut() {
        Pointcut cpc = new AnnotationMatchingPointcut(LogAround.class, true);
        Pointcut mpc = new AnnotationMatchingPointcut(null, LogAround.class, true);
        ComposablePointcut pc = new ComposablePointcut(cpc);
        pc = pc.union(mpc);
        return pc;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
