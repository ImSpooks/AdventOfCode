package me.imspooks.adventofcode.v2025;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;
import me.imspooks.adventofcode.util.NumberConversions;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Day3 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-3/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public long firstPart() {
        String prio = "987654321";

        long result = 0;
        for (String s : this.input) {
//            String input = s.substring(0, s.length() - 1);
//            for (char c : prio.toCharArray()) {
//                int index = input.indexOf(c);
//                if (index != -1) {
//                    String haystack = s.substring(index + 1);
//
//                    for (char cc : prio.toCharArray()) {
//                        if (haystack.indexOf(cc) != -1) {
//                            result += NumberConversions.toLong("" + c + cc);
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }

            result += NumberConversions.toLong(this.findJoltage(s, 2));
        }

        return result;
    }

    @Part(part = 2)
    public long secondPart() {
        long result = 0;
        for (String s : this.input) {
            result += NumberConversions.toLong(this.findJoltage(s, 12));
        }
        return result;
    }

    private String findJoltage(String input, int batteries) {
        StringBuilder result = new StringBuilder();
        int startIndex = 0;

        for (int position = 0; position < batteries; position++) {
            int biggestJoltage = 0;

            for (int i = startIndex; i < input.length(); i++) {
                int joltage = NumberConversions.toInt(input.charAt(i));

                if (joltage > biggestJoltage && input.length() - i >= batteries - position) {
                    biggestJoltage = joltage;
                    startIndex = i + 1;
                }
            }
            result.append(biggestJoltage);
        }

        return result.toString();
    }
}