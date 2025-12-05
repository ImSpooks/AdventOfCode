package me.imspooks.adventofcode.v2025;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;
import me.imspooks.adventofcode.util.NumberConversions;
import me.imspooks.adventofcode.util.Pair;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day5 implements Day {

    private List<String> input;
    private final List<Pair<Long, Long>> freshIds = new ArrayList<>();

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-5/input.txt").getLines(StandardCharsets.UTF_8).toList();

        for (String s : this.input) {
            if (s.isEmpty()) {
                break;
            }

            String[] split = s.split("-");

            long start = NumberConversions.toLong(split[0]);
            long end = NumberConversions.toLong(split[1]);

            freshIds.add(Pair.of(
                    // min max just to be sure
                    Math.min(start, end),
                    Math.max(start, end)
            ));
        }
    }

    @Part(part = 1)
    public long firstPart() {
        long fresh = 0;
        for (String s : this.input.subList(freshIds.size(), this.input.size())) {
            long ingredient = NumberConversions.toLong(s);

            if (freshIds.stream().anyMatch(pair -> ingredient >= pair.first() && ingredient <= pair.second())) {
                fresh++;
            }
        }

        return fresh;
    }

    @Part(part = 2)
    public long secondPart() {
        this.freshIds.sort(Comparator.comparingLong(Pair::first));

        List<Pair<Long, Long>> merged = new ArrayList<>();

        long currentStart = this.freshIds.get(0).first();
        long currentEnd = this.freshIds.get(0).second();

        for (int i = 1; i < this.freshIds.size(); i++) {
            Pair<Long, Long> p = this.freshIds.get(i);
            long start = p.first();
            long end = p.second();

            if (start <= currentEnd) {
                currentEnd = Math.max(currentEnd, end);
            } else {
                merged.add(Pair.of(currentStart, currentEnd));
                currentStart = start;
                currentEnd = end;
            }
        }
        merged.add(Pair.of(currentStart, currentEnd));

        long totalIds = 0;
        for (Pair<Long, Long> p : merged) {
            long start = p.first();
            long end = p.second();

            totalIds += (end - start + 1);
        }

        return totalIds;
    }
}