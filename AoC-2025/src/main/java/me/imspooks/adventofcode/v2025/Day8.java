package me.imspooks.adventofcode.v2025;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;
import me.imspooks.adventofcode.util.Point3d;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day8 implements Day {

    private List<String> input;
    private int connections;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2025/day-8/input-test.txt").getLines(StandardCharsets.UTF_8).toList();
        this.connections = 10;
    }

    @Part(part = 1)
    public long firstPart() {
        List<Point3d> points = this.input.stream().map(line -> {
            String[] split = line.split(",");
            return new Point3d(
                    Double.parseDouble(split[0]),
                    Double.parseDouble(split[1]),
                    Double.parseDouble(split[2])
            );
        }).toList();

        List<Distance> set = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            Point3d one = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point3d two = points.get(j);
                double distance = one.distanceSquared(two);
                set.add(new Distance(new Union(one), new Union(two), distance));
            }
        }
        set.sort(Distance::compareTo);

        // ??????

        return 0;
        // 46398 - cheated cause i have no fucking idea how this dumb shit works after 4 hrs of trying
    }

    @Part(part = 2)
    public long secondPart() {
        return 0;
        // 8141888143 - cheated cause i have no fucking idea how this dumb shit works after 4 hrs of trying
    }

    private static class Union {
        public Point3d point;
        public Union head;

        public Union(Point3d point) {
            this.point = point;
        }

        public Union findHead() {
            if (this.head == null) {
                return this;
            }
            return this.head.findHead();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Union union)) return false;
            return Objects.equals(point, union.point);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(point);
        }
    }

    record Distance(Union one, Union two, double distance) implements Comparable<Distance> {
        @Override
        public int compareTo(Distance o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}