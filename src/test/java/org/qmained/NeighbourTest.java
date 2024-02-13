package org.qmained;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class NeighbourTest {

    @Test
    public void test() {
        Garden garden = new Garden();

        List<Neighbour> neighbours = new ArrayList<>();
        neighbours.add(new Neighbour("Neighbour 1", garden));
        neighbours.add(new Neighbour("Neighbour 2", garden));

        List<Thread> threads = new ArrayList<>();

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (Neighbour neighbour : neighbours) {
                executorService.submit(() -> {
                    threads.add(Thread.currentThread());
                    int amount = 5;
                    System.out.println("[" + neighbour.getName() + "] Amount of times: " + amount);
                    for (int i = 0; i < amount; i++) {
                        neighbour.collectBerries(15);
                    }
                });
            }
            executorService.shutdown();
        }

        // Test no memory consistency errors
        assertEquals(350, garden.getAmount());

        // Test no dead/soft locks
        for (Thread thread : threads) {
            assertFalse(thread.isAlive());
        }

    }


}
