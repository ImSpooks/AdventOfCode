package me.imspooks.adventofcode.v2025;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Day7 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-7/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public long firstPart() {
        return this.calculate().split;
    }

    @Part(part = 2)
    public long secondPart() {
        return this.calculate().total;
    }

    private Day7Result calculate() {
        int length = this.input.get(0).length();
        long[][] values = new long[this.input.size()][length];
        int split = 0;

        values[0][this.input.get(0).indexOf("S")] = 1;
        for (int row = 1; row < this.input.size(); row++) {
            String line = this.input.get(row);

            char[] chars = line.toCharArray();
            for (int col = 0; col < chars.length; col++) {
                if (values[row - 1][col] == 0) continue;

                if (chars[col] == '^') {
                    split++;
                    values[row][col - 1] += values[row - 1][col];
                    values[row][col + 1] += values[row - 1][col];
                } else {
                    values[row][col] += values[row - 1][col];
                }
            }
        }

        long total = 0;
        for (int i = 0; i < values[values.length - 1].length; i++) {
            total += values[values.length - 1][i];
        }
        return new Day7Result(split, total);
    }



    private record Day7Result(int split, long total) {

    }
}