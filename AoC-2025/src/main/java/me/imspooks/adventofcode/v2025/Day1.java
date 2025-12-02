package me.imspooks.adventofcode.v2025;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;
import me.imspooks.adventofcode.util.NumberConversions;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Day1 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-1/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public int firstPart() {
        return calc(false);
    }

    @Part(part = 2)
    public int secondPart() {
        return calc(true);
    }

    public int calc(boolean stepByStep) {
        int dial = 50;
        int times = 0;

        for (String s : this.input) {
            if (s.isEmpty())
                continue;

            boolean add = s.charAt(0) == 'R';
            int amount = NumberConversions.toInt(s.substring(1));

            if (!stepByStep) {
                dial += add ? amount : -amount;
                if (dial % 100 == 0) {
                    times++;
                }
            } else {
                for (int i = 0; i < amount; i++) {
                    dial += add ? 1 : -1;
                    if (dial % 100 == 0) {
                        times++;
                    }
                }
            }
        }

        return times;
    }
}