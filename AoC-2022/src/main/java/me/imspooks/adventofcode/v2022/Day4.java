package me.imspooks.adventofcode.v2022;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nick on 04 dec. 2022.
 * Copyright © ImSpooks
 */
public class Day4 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2022/day-4/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }


    @Part(part = 1)
    public long firstPart() {
        Pattern pattern = Pattern.compile("^(\\d*?)-(\\d*?),(\\d*?)-(\\d*?)$");
        return this.input.stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .filter(matcher -> {
                    int firstStart = Integer.parseInt(matcher.group(1));
                    int firstEnd = Integer.parseInt(matcher.group(2));
                    int secondStart = Integer.parseInt(matcher.group(3));
                    int secondEnd = Integer.parseInt(matcher.group(4));

                    return (firstStart <= secondStart && firstEnd >= secondEnd) || (firstStart >= secondStart && firstEnd <= secondEnd);
                }).count();
    }

    @Part(part = 2)
    public long secondPart() {
        Pattern pattern = Pattern.compile("^(\\d*?)-(\\d*?),(\\d*?)-(\\d*?)$");
        return this.input.stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .filter(matcher -> {
                    int firstStart = Integer.parseInt(matcher.group(1));
                    int firstEnd = Integer.parseInt(matcher.group(2));
                    int secondStart = Integer.parseInt(matcher.group(3));
                    int secondEnd = Integer.parseInt(matcher.group(4));

                    for (int i = firstStart; i <= firstEnd; i++) {
                        if (i >= secondStart && i <= secondEnd) {
                            return true;
                        }
                    }
                    return false;
                }).count();
    }
}