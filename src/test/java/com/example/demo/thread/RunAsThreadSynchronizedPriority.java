package com.example.demo.thread;

import com.example.demo.space.SpaceShip;
import org.junit.jupiter.api.Test;

public class RunAsThreadSynchronizedPriority {
    class AddFuel extends Thread{
        SpaceShip ship;
        public AddFuel(SpaceShip ship) {
            this.ship = ship;
        }

        @Override
        public void run() {
            synchronized (ship) {
                for (int i = 0; i < 70; i++) {
                    ship.setFuel(ship.getFuel()+1);
                    System.out.println("Add fuel to SpaceShip " + ship);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class RemoveFuel extends Thread{
        SpaceShip ship;
        public RemoveFuel(SpaceShip ship) {
            this.ship = ship;
        }
        @Override
        public void run() {
            synchronized (ship) {
                for (int i = 0; i < 40; i++) {
                    ship.setFuel(ship.getFuel()-1);
                    System.out.println("Remove fuel from SpaceShip " + ship);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Test
    void tryThread() throws InterruptedException {
        System.out.println("Thread test BEGIN");
        SpaceShip spaceShip = new SpaceShip("Round", "Mike", 3);

        AddFuel addFuel = new AddFuel(spaceShip);
        RemoveFuel removeFuel = new RemoveFuel(spaceShip);
        addFuel.setPriority(Thread.MIN_PRIORITY);
        removeFuel.setPriority(Thread.MAX_PRIORITY);
        addFuel.start();
        removeFuel.start();
        System.out.println("After thread has been set to start");
        addFuel.join();
        removeFuel.join();
        System.out.println("Thread test END");
    }
}
