package org.demo.spring.proxyselfcall.case1;


import org.demo.spring.proxyselfcall.logaround.LogAround;
import org.springframework.stereotype.Service;

@Service
public class Case1Service {

    @LogAround("case1 log")
    public void doSomething() {
        System.out.println("case1: doing something...");
    }

}
