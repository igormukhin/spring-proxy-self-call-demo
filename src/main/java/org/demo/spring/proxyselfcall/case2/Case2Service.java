package org.demo.spring.proxyselfcall.case2;


import org.demo.spring.proxyselfcall.logaround.LogAround;
import org.springframework.stereotype.Service;

@Service
public class Case2Service {

    public void callSibling() {
        doSomething();
    }

    @LogAround("case2 log")
    public void doSomething() {
        System.out.println("case2: doing something...");
    }

}
