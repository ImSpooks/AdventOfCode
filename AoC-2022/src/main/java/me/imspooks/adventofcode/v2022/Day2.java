package me.imspooks.adventofcode.v2022;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Day2 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2022/day-2/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public int firstPart() {
        int points = 0;

        for (String s : this.input) {
            String[] options = s.split(" ");

            int opponent = "ABC".indexOf(options[0]);
            int you = "XYZ".indexOf(options[1]);

            points += you + 1;

            int outcome = (you - opponent + 3) % 3;

            switch (outcome) {
                case 1 -> points += 6;
                case 0 -> points += 3;
            }
        }

        return points;
    }

    @Part(part = 2)
    public int secondPart() {
        int points = 0;

        for (String s : this.input) {
            String[] options = s.split(" ");

            // rock = 1
            // paper = 2
            // scissors = 3

            int opponent = "ABC".indexOf(options[0]);

            switch (options[1]) {
                case "X" -> points += (opponent - 1 + 3) % 3 + 1;
                case "Y" -> points += 3 + opponent + 1;
                case "Z" -> points += 6 + (opponent + 1 + 3) % 3 + 1;
            }
        }

        return points;
    }
}
