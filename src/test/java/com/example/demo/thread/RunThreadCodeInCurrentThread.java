package com.example.demo.thread;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

public class RunThreadCodeInCurrentThread {

    @Test
    void tryThread() throws InterruptedException {
        System.out.println("Thread test BEGIN");
        Thread thread = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println("I'm counting " + i);
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        );
        thread.run();
        System.out.println("After thread has been set to run");
//        thread.join();
        System.out.println("Thread test END");
    }
}
