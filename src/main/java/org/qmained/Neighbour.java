package org.qmained;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Neighbour {

    private static final Lock lock = new ReentrantLock(true);
    private int progress;
    private final String name;
    private final Garden garden;

    public Neighbour(String name, Garden garden) {
        this.name = name;
        this.garden = garden;
    }

    public void collectBerries() {
        collectBerries(ThreadLocalRandom.current().nextInt(30));
    }

    public void collectBerries(int amount) {
        lock.lock();
        try {
            System.out.println("[" + name + "] Berries to collect: " + amount);
            for (progress = 1; progress <= amount; progress++) {
                garden.getBerry();
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
