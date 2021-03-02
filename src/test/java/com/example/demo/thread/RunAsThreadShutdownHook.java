package com.example.demo.thread;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

public class RunAsThreadShutdownHook {

    @Test
    void tryThread() throws InterruptedException {
        System.out.println("Thread test BEGIN");
        Thread thread = new Thread(() -> {
                System.out.println("The JVM is about to shutdown... Do some stuff just before.");
        });
        Runtime.getRuntime().addShutdownHook(thread);
        System.out.println("Hook has been set");
        Thread.sleep(4000);
        System.out.println("Thread test END");
    }
}
