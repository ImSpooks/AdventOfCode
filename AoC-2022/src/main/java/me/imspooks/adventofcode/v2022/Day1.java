package me.imspooks.adventofcode.v2022;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day1 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2022/day-1/input.txt").getLines(StandardCharsets.UTF_8).toList();
//        input = new BufferedReader(new InputStreamReader(Day1.class.getResourceAsStream("/day-1/input.txt"), StandardCharsets.UTF_8)).lines().toList();
    }

    @Part(part = 1)
    public int firstPart() {
        return this.getSorted().get(0);
    }

    @Part(part = 2)
    public int secondPart() {
        List<Integer> elves = this.getSorted();
        return elves.get(0) + elves.get(1) + elves.get(2);
    }

    private List<Integer> getSorted() {
        List<Integer> elves = new ArrayList<>();

        int snacks = 0;
        for (String s : input) {
            if (s.isEmpty()) {
                elves.add(snacks);
                snacks = 0;
                continue;
            }

            try {
                snacks = snacks + Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }

        elves.sort((o1, o2) -> o2 - o1);
        return elves;
    }
}
