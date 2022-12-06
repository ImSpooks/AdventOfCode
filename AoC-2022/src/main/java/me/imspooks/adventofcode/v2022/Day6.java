package me.imspooks.adventofcode.v2022;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by Nick on 04 dec. 2022.
 * Copyright © ImSpooks
 */
public class Day6 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2022/day-6/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }


    @Part(part = 1)
    public int firstPart() {
        return this.getMarker(this.input.get(0), 4);
    }

    @Part(part = 2)
    public int secondPart() {
        return this.getMarker(this.input.get(0), 14);
    }

    private int getMarker(String input, int length) {
        String[] characters = input.split("");

        for (int i = 0; i < characters.length - length; i++) {
            String string = input.substring(i, i + length);

            if (!hasDuplicate(string)) {
                return i + length;
            }
        }
        return 0;
    }

    private boolean hasDuplicate(CharSequence g) {
        for (int i = 0; i < g.length(); i++) {
            for (int j = i + 1; j < g.length(); j++) {
                if (g.charAt(i) == g.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}