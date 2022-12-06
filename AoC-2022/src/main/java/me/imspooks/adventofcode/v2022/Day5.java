package me.imspooks.adventofcode.v2022;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nick on 04 dec. 2022.
 * Copyright © ImSpooks
 */
public class Day5 implements Day {

    private int skip;
    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2022/day-5/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }


    @Part(part = 1)
    public String firstPart() {
        List<LinkedList<String>> sections = this.getContainers();

        Pattern pattern = Pattern.compile("^move (\\d*?) from (\\d*?) to (\\d*?)$");

        for (int i = this.skip; i < this.input.size(); i++) {
            String command = this.input.get(i);

            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                int boxes = Integer.parseInt(matcher.group(1));
                int from = Integer.parseInt(matcher.group(2)) - 1;
                int to = Integer.parseInt(matcher.group(3)) - 1;

                for (int boxNr = 0; boxNr < boxes; boxNr++) {
                    String box = sections.get(from).removeFirst();
                    sections.get(to).offerFirst(box);
                }
            }
        }

        StringBuilder result = new StringBuilder();

        for (LinkedList<String> section : sections) {
            result.append(section.getFirst());
        }

        return result.toString();
    }

    @Part(part = 2)
    public String secondPart() {
        List<LinkedList<String>> sections = this.getContainers();

        Pattern pattern = Pattern.compile("^move (\\d*?) from (\\d*?) to (\\d*?)$");

        for (int i = this.skip; i < this.input.size(); i++) {
            String command = this.input.get(i);

            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                int boxes = Integer.parseInt(matcher.group(1));
                int from = Integer.parseInt(matcher.group(2)) - 1;
                int to = Integer.parseInt(matcher.group(3)) - 1;


                StringBuilder output = new StringBuilder();
                for (int boxNr = 0; boxNr < boxes; boxNr++) {
                    output.append(sections.get(from).removeFirst());
                }
                for (int length = output.length(); length > 0; length--) {
                    sections.get(to).offerFirst(String.valueOf(output.charAt(length - 1)));
                }
            }
        }

        StringBuilder result = new StringBuilder();

        for (LinkedList<String> section : sections) {
            result.append(section.getFirst());
        }

        return result.toString();
    }

    private List<LinkedList<String>> getContainers() {
        List<LinkedList<String>> sections = new ArrayList<>();

        for (String line : this.input) {
            skip++;
            if (line.isEmpty()) {
                break;
            }
            if (line.startsWith(" ") && !line.startsWith("  ")) {
                // numbers
                continue;
            }

            for (int i = 0; i < line.length() / 4 + 1; i++) {
                int substring = i * 4 + 1;
                String character = line.substring(substring, substring + 1);

                if (sections.size() <= i) {
                    sections.add(new LinkedList<>());
                }

                if (!character.isBlank()) {
                    sections.get(i).add(character);
                }
            }
        }
        return sections;
    }
}