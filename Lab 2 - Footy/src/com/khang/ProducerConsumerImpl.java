package com.khang;
import java.util.concurrent.Semaphore;

public class ProducerConsumerImpl {

    private final Semaphore empty = new Semaphore(1);
    private final Semaphore full = new Semaphore(0);
    private boolean teaching;
    public void put() throws InterruptedException {
        synchronized (this) {
            empty.acquire();
            full.release();
            while (!teaching) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            teaching = false;
            notifyAll();
            Thread.sleep(3000);
            System.out.printf("%s is explaining; %n", Thread.currentThread().getName());
        }
    }

    public void get() throws InterruptedException {
        synchronized (this) {
            while (teaching) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            teaching = true;
            notifyAll();
            full.acquire();
            System.out.printf("%s is consulting; %n", Thread.currentThread().getName());
            empty.release();
        }
    }
}
