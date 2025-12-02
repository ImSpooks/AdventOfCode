package me.imspooks.adventofcode.v2022;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;
import me.imspooks.adventofcode.util.Position;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by Nick on 09 dec. 2022.
 * Copyright © ImSpooks
 */
public class Day9 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2022/day-9/input-test.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public int firstPart() {
        Position head = new Position(0, 0);
        Position tail = new Position(0, 0);

        for (String s : this.input) {
            String[] split = s.split(" ");
            String direction = split[0];
            int movements = Integer.parseInt(split[1]);

            switch (direction) {
                case "R" -> { // RIGHT

                }
                case "U" -> { // UP

                }
                case "L" -> { // LEFT

                }
                case "D" -> { // DOWN

                }
            }
        }

        return 0;
    }

    @Part(part = 2)
    public int secondPart() {
        return 0;
    }

    public boolean attached(Position pos1, Position pos2) {
        int xDiff = Math.abs(pos1.x() - pos2.x());
        int yDiff = Math.abs(pos1.y() - pos2.y());

        return xDiff <= 1 && yDiff <= 1;
    }
}