package me.imspooks.adventofcode.v2025;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;
import me.imspooks.adventofcode.util.NumberConversions;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day2 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-2/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public long firstPart() {
        String line = this.input.get(0);

        List<Long> invalidIds = new ArrayList<>();

        for (String product : line.split(",")) {
            String[] split = product.split("-");

            long first = NumberConversions.toInt(split[0]);
            long second = NumberConversions.toInt(split[1]);

            for (long i = first; i <= second; i++) {
                String number = String.valueOf(i);

                int length = number.length();
                if (length % 2 != 0) {
                    continue;
                }

                String needle = number.substring(0, length / 2);
                String haystack = number.substring(length / 2, length);

                if (haystack.equals(needle)) {
                    invalidIds.add(NumberConversions.toLong(number));
                    System.out.println(product + " = " + number);
                }
            }
        }

        long total = 0;
        for (long id : invalidIds) {
            total += id;
        }

        return total;
    }

    @Part(part = 2)
    public long secondPart() {
        String line = this.input.get(0);

        List<Long> invalidIds = new ArrayList<>();

        for (String product : line.split(",")) {
            String[] split = product.split("-");

            long first = NumberConversions.toLong(split[0]);
            long second = NumberConversions.toLong(split[1]);

            for (long i = first; i <= second; i++) {
                String number = String.valueOf(i);

                int digits = number.length();
                List<Integer> factors = this.factors(digits);
                for (int ii = 0; ii < factors.size(); ii++) {

                    int subDivision = digits / factors.get(ii);

                    if (isInvalid(digits, subDivision, number)) {
                        invalidIds.add(Long.parseLong(number));
                        break;
                    }
                }
            }
        }

        long total = 0;
        for (long id : invalidIds) {
            total += id;
        }

        return total;
    }

    private boolean isInvalid(int length, int subDivision, String test) {
        List<String> divisions = new ArrayList<>();
        for (int i = 0; i < length; i += subDivision) {
            divisions.add(test.substring(i, i + subDivision));
        }
        for (int i = 1; i < divisions.size(); i++) {
            if (!divisions.get(i).equals(divisions.get(0))) {
                return false;
            }
        }
        return true;

    }

    private List<Integer> factors(int number) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) result.add(i);
        }
        return result;
    }
}