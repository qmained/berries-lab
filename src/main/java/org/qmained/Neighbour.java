package org.qmained;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Neighbour {

    private static final Lock lock = new ReentrantLock(true);
    private int progress;
    private final String name;

    public Neighbour(String name) {
        this.name = name;
    }

    public void collectBerries() {
        lock.lock();
        try {
            int randomBerriesNumber = ThreadLocalRandom.current().nextInt(30);
            System.out.println("[" + name + "] Berries to collect: " + randomBerriesNumber);
            for (progress = 1; progress <= randomBerriesNumber; progress++) {
                try {
                    Thread.sleep(100 + ThreadLocalRandom.current().nextInt(100));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("[" + name + "] Collected " + progress + " berries.");
            }
            System.out.println("[" + name + "] All berries collected.");
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        return name;
    }
}
