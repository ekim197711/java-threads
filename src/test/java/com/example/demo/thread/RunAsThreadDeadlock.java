package com.example.demo.thread;

import com.example.demo.space.SpaceShip;
import org.junit.jupiter.api.Test;

public class RunAsThreadDeadlock {
    class AddFuel extends Thread{
        SpaceShip primaryShip;
        SpaceShip secondaryShip;

        public AddFuel(SpaceShip primaryShip, SpaceShip secondaryShip) {
            this.primaryShip = primaryShip;
            this.secondaryShip = secondaryShip;
        }

        @Override
        public void run() {
            synchronized (primaryShip) {
                for (int i = 0; i < 70; i++) {
                    primaryShip.setFuel(primaryShip.getFuel()+1);
                    System.out.println("Add fuel to primary SpaceShip " + primaryShip);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (secondaryShip){
                    for (int i = 0; i < 40; i++) {
                        secondaryShip.setFuel(secondaryShip.getFuel()+1);
                        System.out.println("Add fuel to Secondary SpaceShip " + secondaryShip);
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    class RemoveFuel extends Thread{
        SpaceShip primaryShip;
        SpaceShip secondaryShip;

        public RemoveFuel(SpaceShip primaryShip, SpaceShip secondaryShip) {
            this.primaryShip = primaryShip;
            this.secondaryShip = secondaryShip;
        }

        @Override
        public void run() {
            synchronized (secondaryShip) {
                for (int i = 0; i < 50; i++) {
                    secondaryShip.setFuel(secondaryShip.getFuel()-1);
                    System.out.println("Remove fuel to secondary SpaceShip " + secondaryShip);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (primaryShip){
                    for (int i = 0; i < 40; i++) {
                        primaryShip.setFuel(primaryShip.getFuel()+1);
                        System.out.println("Remove fuel from Secondary SpaceShip " + primaryShip);
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Test
    void tryThread() throws InterruptedException {
        System.out.println("Thread test BEGIN");
        SpaceShip secondaryspaceShip = new SpaceShip("Round", "Mike", 3);
        SpaceShip primaryspaceShip = new SpaceShip("Pyramid", "Susan", 5);

        AddFuel addFuel = new AddFuel(primaryspaceShip, secondaryspaceShip);
        RemoveFuel removeFuel = new RemoveFuel(primaryspaceShip, secondaryspaceShip);
        addFuel.start();
        removeFuel.start();
        System.out.println("After thread has been set to start");
        addFuel.join();
        removeFuel.join();
        System.out.println("Thread test END");
    }
}
