package me.imspooks.adventofcode.v2022;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day3 implements Day {

    private List<String> input;
    private final String indexes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2022/day-3/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public int firstPart() {
        AtomicInteger result = new AtomicInteger(0);

        for (String s : this.input) {
            // Split parts into 2 sections but cutting them in half
            String first = s.substring(0, s.length() / 2);
            String second = s.substring(s.length() / 2);

//            System.out.println("------");
//            System.out.println("first  = " + first);
//            System.out.println("second = " + second);

            // Check all common letter types and put them in our results map
            Map<String, Integer> results = new HashMap<>();
            Arrays.stream(first.split(""))
                    .map(character -> second.contains(character) ? character : "")
                    .filter(character -> character.length() > 0)
                    .forEach(character -> results.put(character, results.getOrDefault(character, 0) + 1));

            // Get the most common letter via sort
            results.entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(entry -> {
                String character = entry.getKey();
                int index = this.indexes.indexOf(character) + 1;
                result.addAndGet(index);
            });
        }

        return result.get();
    }

    @Part(part = 2)
    public int secondPart() {
        AtomicInteger result = new AtomicInteger(0);

        for (int i = 0; i < this.input.size(); i += 3) {
            // Get our group
            String first = this.input.get(i);
            String second = this.input.get(i + 1);
            String third = this.input.get(i + 2);

            Arrays.stream(this.indexes.split(""))
                    .filter(character -> first.contains(character) && second.contains(character) && third.contains(character))
                    .findFirst().ifPresent(character -> {
                        int index = this.indexes.indexOf(character) + 1;
                        result.addAndGet(index);
                    });
        }

        return result.get();
    }
}
