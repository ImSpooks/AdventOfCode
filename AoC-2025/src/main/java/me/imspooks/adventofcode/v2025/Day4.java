package me.imspooks.adventofcode.v2025;

import lombok.RequiredArgsConstructor;
import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;
import me.imspooks.adventofcode.util.Pair;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 implements Day {

    private List<String> input;
    private int[][] map;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-4/input.txt").getLines(StandardCharsets.UTF_8).toList();

        this.map = new int[this.input.size()][this.input.get(0).length()];

        for (int x = 0; x < this.input.size(); x++) {
            String line = this.input.get(x);
            for (int y = 0; y < line.length(); y++) {
                this.map[x][y] = line.charAt(y) == '.' ? 0 : 1;
            }
        }
    }

    @Part(part = 1)
    public long firstPart() {
        int papers = 0;
        int unobtainable = 0;

        for (int x = 0; x < this.map.length; x++) {
            for (int y = 0; y < this.map[0].length; y++) {
                if (this.map[x][y] == 1) {
                    papers++;

                    int papersAdjacent = getPapersAdjacent(x, y);
                    if (papersAdjacent > 4) {
                        unobtainable++;
                    }
                }
            }
        }

        return papers - unobtainable;
    }

    @Part(part = 2)
    public long secondPart() {
        int removedTotal = 0;


        Map<Pair<Integer, Integer>, Integer> removed = new HashMap<>();
        do {
            removedTotal += removed.size();
            removed.forEach((x, y) -> {
                this.map[x.key()][x.value()] = 0;
            });
            removed.clear();

            for (int x = 0; x < this.map.length; x++) {
                for (int y = 0; y < this.map[0].length; y++) {
                    if (this.map[x][y] == 1) {

                        int papersAdjacent = getPapersAdjacent(x, y);
                        if (papersAdjacent <= 4) {
                            removed.put(Pair.of(x, y), papersAdjacent);
                        }
                    }
                }
            }

        } while (!removed.isEmpty());

        return removedTotal;
    }

    private int getPapersAdjacent(int x, int y) {
        int papersAdjacent = 0;

        for (int relativeX = -1; relativeX <= 1; relativeX++) {
            for (int relativeY = -1; relativeY <= 1; relativeY++) {
                int checkX = x + relativeX;
                int checkY = y + relativeY;

                if (checkX < 0 || checkY < 0 || checkX >= this.map.length || checkY >= this.map[0].length) {
                    continue;
                }

                if (this.map[checkX][checkY] == 1) {
                    papersAdjacent++;
                }
            }
        }
        return papersAdjacent;
    }
}