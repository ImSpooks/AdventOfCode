package me.imspooks.adventofcode.v2025;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;
import me.imspooks.adventofcode.util.NumberConversions;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-6/input.txt").getLines(StandardCharsets.UTF_8).filter(s -> !s.isEmpty()).collect(Collectors.toList());


    }

    @Part(part = 1)
    public long firstPart() {
        long result = 0;

        // OLD SOLUTION
//        // Remove double spaces so we can nicely split by a single space
//        for (int i = 0; i < this.input.size(); i++) {
//            String s = this.input.get(i);
//            while (s.startsWith(" ")) {
//                s = s.substring(1);
//            }
//
//            do {
//                s = s.replace("  ", " ");
//            } while (s.contains("  "));
//
//            while (s.endsWith(" ")) {
//                s = s.substring(0, s.length() - 1);
//            }
//
//            this.input.set(i, s);
//        }
//
//        // col -> row
//        long[][] map = new long[this.input.get(0).split(" ").length][this.input.size() - 1];
//
//        for (int row = 0; row < this.input.size() - 1; row++) {
//            String s = this.input.get(row);
//
//            int col = 0;
//            for (String string : s.split(" ")) {
//                long number = Long.parseLong(string);
//                map[col++][row] = number;
//            }
//        }
//
//        String[] operations = this.input.get(this.input.size() - 1).split(" ");
//        for (int col = 0; col < map.length; col++) {
//            long[] sum = map[col];
//
//            String operation = operations[col];
//
//            long total = sum[0];
//            for (int row = 1; row < sum.length; row++) {
//                switch (operation) {
//                    case "+" -> total += sum[row];
//                    case "*" -> total *= sum[row];
//                }
//            }
//            result += total;
//            // System.out.println("col " + col + " (" + Arrays.toString(sum) + ") \"" + operation + "\" = " + total);
//        }

        // New solution with part 2 in mind

        List<List<String>> map = this.parseMap();
        int size = map.get(0).size();
        for (int col = size; col-- > 0; ) {
            int rows = map.size();

            List<String> colData = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                colData.add(map.get(row).get(col));
            }

            List<Long> sumData = new ArrayList<>();
            for (int i = 0; i < colData.size() - 1; i++) {
                String number = colData.get(i);
                while (number.contains(" "))
                    number = number.replace(" ", "");

                sumData.add(NumberConversions.toLong(number));
            }

            result = getResult(result, colData, sumData);
        }

        return result;
    }

    @Part(part = 2)
    public long secondPart() {
        long result = 0;

        List<List<String>> map = this.parseMap();

        int size = map.get(0).size();
        for (int col = size; col-- > 0; ) {
            int rows = map.size();

            List<String> colData = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                colData.add(map.get(row).get(col));
            }

            int length = colData.stream().mapToInt(String::length).max().orElse(0);

            List<Long> sumData = new ArrayList<>();
            for (int l = length; l > 0; l--) {
                StringBuilder value = new StringBuilder();

                for (int i = 0; i < colData.size() - 1; i++) {
                    String number = colData.get(i);
                    if (number.length() < l)
                        continue;

                    char c = number.charAt(l - 1);
                    if (c != ' ') {
                        value.append(c);
                    }
                }
                if (!value.isEmpty())
                    sumData.add(NumberConversions.toLong(value));
            }

            result = getResult(result, colData, sumData);
            // System.out.println("col " + col + " (" + sumData + ") \"" + operation + "\" = " + total);
        }

        return result;
    }

    private long getResult(long result, List<String> colData, List<Long> sumData) {
        String operation = colData.get(colData.size() - 1);
        do {
            operation = operation.replace(" ", "");
        } while (operation.contains(" "));

        long total = sumData.get(0);
        for (int i = 1; i < sumData.size(); i++) {
            switch (operation) {
                case "+" -> total += sumData.get(i);
                case "*" -> total *= sumData.get(i);
            }
        }

        result += total;
        return result;
    }

    private List<List<String>> parseMap() {
        List<List<String>> map = new ArrayList<>();

        List<Integer> lengths = new ArrayList<>();
        String operations = this.input.get(this.input.size() - 1);
        for (int i = 0, col = 0; i < operations.length(); i++) {
            char c = operations.charAt(i);
            if (i > 0 && (c != ' ') || i == operations.length() - 1) {
                lengths.add(col - 1);
                col = 0;
            }
            col++;
        }
        lengths.add(0); // last one isn't parsed

        // row -> col
        for (int row = 0; row < this.input.size(); row++) {
            map.add(new ArrayList<>());

            String s = this.input.get(row);

            int startIndex = 0;
            for (int i = 0; i < lengths.size(); i++) {
                int length = lengths.get(i);
                if (i == lengths.size() - 1) {
                    // remaining length
                    length = s.length() - startIndex;
                }

                String number = s.substring(startIndex, Math.min(startIndex + length, s.length()));

                map.get(row).add(number);
                startIndex += lengths.get(i) + 1;
            }
        }

        return map;
    }
}