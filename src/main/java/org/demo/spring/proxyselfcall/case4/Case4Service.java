package org.demo.spring.proxyselfcall.case4;


import org.demo.spring.proxyselfcall.logaround.LogAround;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class Case4Service {

    private final ObjectProvider<Case4Service> proxiedThis;

    public Case4Service(ObjectProvider<Case4Service> proxiedThis) {
        this.proxiedThis = proxiedThis;
    }

    public void callSibling() {
        proxiedThis.getObject().doSomething();
    }

    @LogAround("case4 log")
    public void doSomething() {
        System.out.println("case4: doing something...");
    }

}
