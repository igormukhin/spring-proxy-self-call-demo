package org.demo.spring.proxyselfcall.logaround;


import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.stereotype.Component;

@Component
class LogAroundBeanPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor {
    public LogAroundBeanPostProcessor() {
        this.advisor = new LogAroundAnnotationAdvisor();
    }
}
