package org.demo.spring.proxyselfcall.logaround;


import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

public class LogAroundCounter {

    private static final AtomicInteger counter = new AtomicInteger(0);

    static int increment() {
        return counter.incrementAndGet();
    }

    public static int getCount() {
        return counter.get();
    }

}
