package com.example.demo.thread;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

public class RunAsThread {

    @Test
    void tryThread() throws InterruptedException {
        System.out.println("Thread test BEGIN");
        Thread thread = new Thread(
                new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("I'm counting " + i);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        System.out.println("After thread has been set to start");
        thread.join();
        System.out.println("Thread test END");
    }
}
