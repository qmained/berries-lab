package org.qmained;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        Garden garden = new Garden();

        List<Neighbour> neighbours = new ArrayList<>();
        neighbours.add(new Neighbour("Neighbour 1", garden));
        neighbours.add(new Neighbour("Neighbour 2", garden));

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (Neighbour neighbour : neighbours) {
                executorService.submit(() -> {
                    int amount = 5 + ThreadLocalRandom.current().nextInt(5);
                    System.out.println("[" + neighbour.getName() + "] Amount of times: " + amount);
                    for (int i = 0; i < amount; i++) {
                        neighbour.collectBerries();
                    }
                });
            }
        }



    }
}