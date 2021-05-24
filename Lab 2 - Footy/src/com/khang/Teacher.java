package com.khang;
import java.util.concurrent.Semaphore;

public class Teacher extends Thread {
    ProducerConsumerImpl pc;
    Semaphore chairPermit;

    public Teacher (ProducerConsumerImpl knowledge, Semaphore chairPermit) {
        super("Professor");
        this.pc = knowledge;
        this.chairPermit = chairPermit;
    }

    @Override public void run() {
        try {
            while (true)
                pc.put();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
