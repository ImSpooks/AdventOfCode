package me.imspooks.adventofcode.v2025;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class DayTemplate implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-x/input-test.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public int firstPart() {
        return 0;
    }

    @Part(part = 2)
    public int secondPart() {
        return 0;
    }
}