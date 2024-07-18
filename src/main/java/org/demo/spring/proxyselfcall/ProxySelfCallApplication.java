package org.demo.spring.proxyselfcall;


import org.demo.spring.proxyselfcall.case1.Case1Service;
import org.demo.spring.proxyselfcall.case2.Case2Service;
import org.demo.spring.proxyselfcall.case3.Case3Service;
import org.demo.spring.proxyselfcall.case4.Case4Service;
import org.demo.spring.proxyselfcall.logaround.LogAroundCounter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ProxySelfCallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxySelfCallApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(Case1Service case1Service, Case2Service case2Service, Case3Service case3Service, Case4Service case4Service) {
        return _ -> {
            // case 1
            System.out.println("======================");
            System.out.println("Baseline: Direct call to the annotated method");
            case1Service.doSomething();
            System.out.println("count: " + LogAroundCounter.getCount());

            // case 2
            System.out.println("======================");
            System.out.println("JDKProxy: Annotation will not work");
            case2Service.callSibling();
            System.out.println("count: " + LogAroundCounter.getCount());

            // case 3
            System.out.println("======================");
            System.out.println("CGLibProxy: Annotation will not work");
            case3Service.callSibling();
            System.out.println("count: " + LogAroundCounter.getCount());

            // case 4
            System.out.println("======================");
            System.out.println("Getting the proxied object from the ObjectProvider helps");
            case4Service.callSibling();
            System.out.println("count: " + LogAroundCounter.getCount());
        };
    }
}

