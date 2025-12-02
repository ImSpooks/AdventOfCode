package me.imspooks.adventofcode.v2022;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;
import me.imspooks.adventofcode.util.InputReader;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day7 implements Day {

    private List<String> input;

    @Override
    public void preRun() {
        this.input = InputReader.of("/aoc-2022/day-7/input.txt").getLines(StandardCharsets.UTF_8).toList();
    }

    @Part(part = 1)
    public long firstPart() {
        final Directory root = this.walk(true, "/", this.input.subList(1, this.input.size()));

//        root.print(0);
//        System.out.println("------------------------");
//        for (Directory directory : root.walkDirectories()) {
//            System.out.println(directory.name + " (size=" + directory.size() + ",fullSize=" + directory.totalSize() + ")");
//        }
//        System.out.println("------------------------");
//        for (File file : root.walkFiles()) {
//            System.out.println(file.name + " (size=" + file.size + ")");
//        }

        return root.walkDirectories().stream()
                .mapToLong(Directory::totalSize)
                .filter(size -> size <= 100_000)
                .sum();
    }

    @Part(part = 2)
    public long secondPart() {
        final Directory root = this.walk(true, "/", this.input.subList(1, this.input.size()));

        long totalSize = 70_000_000;
        long rootSize = root.totalSize();
        long available = totalSize - rootSize;

        long mustBeFree = 30_000_000;

        List<Directory> toCheck = new ArrayList<>();
        toCheck.add(root);
        toCheck.addAll(root.walkDirectories());

        List<Directory> directories = new ArrayList<>();

        for (Directory directory : toCheck) {
            long newAvailable = available + directory.totalSize();
//            System.out.println("--------------");
//            System.out.println("Deleting \"" + directory.name + " (size=" + directory.size() + ",fullSize=" + directory.totalSize() + ")"
//                    + "\" will result to " + newAvailable + " free space");

            if (newAvailable >= mustBeFree) {
//                System.out.println("Dir can be deleted.");
                directories.add(directory);
            } else {
//                System.out.println("Dir can not be deleted.");
            }
        }

        directories.sort(Comparator.comparingLong(Directory::totalSize));

        return directories.get(0).totalSize();
    }

    private Directory walk(boolean isRoot, String name, List<String> input) {
        final List<Directory> subDirectories = new ArrayList<>();
        final List<File> files = new ArrayList<>();

        final Directory root = new Directory(name, subDirectories, files);


        int open = 0;
        for (int i = 0; i < input.size(); i++) {
            String command = input.get(i);

            if (command.startsWith("$ ")) {
                command = command.substring(2);

                if (command.startsWith("cd ")) {
                    if (command.equals("cd ..")) {
                        if (open == 0) {
                            // We are in our starting point, break the loop
                            break;
                        }
                        open--;
                    } else {
                        // We cd'd into another folder, add it as a sub folder
                        if (open == 0) {
                            String dirName = command.substring(3);
                            // Copy our input from what we have and add 1 to the starting index, to prevent a stack overflow
                            List<String> next = input.subList(i + 1, input.size());
                            Directory directory = this.walk(false, dirName, next);

                            subDirectories.add(directory);
                            ;
                        }
                        open++;
                    }
                }
            } else {
                if (open == 0) {
                    // File found in our directory
                    if (!command.startsWith("dir ")) {
                        String[] split = command.split(" ");

                        File file = new File(split[1], Long.parseLong(split[0]));
                        files.add(file);
                    }
                }
            }
        }

        return root;
    }

    private static final String TAB = "  ";

    private record Directory(String name, List<Directory> directories, List<File> files) {
        public void print(int tabs) {
            String prefix = TAB.repeat(tabs);
            System.out.println(prefix + "- " + this.name + " (dir)");

            for (Directory directory : this.directories) {
                directory.print(tabs + 1);
            }
            for (File file : this.files) {
                file.print(tabs + 1);
            }
        }

        public long size() {
            return this.files.stream().mapToLong(File::size).sum();
        }

        public long totalSize() {
            return this.walkFiles().stream().mapToLong(File::size).sum();
        }

        public List<Directory> walkDirectories() {
            List<Directory> directories = new ArrayList<>(this.directories);

            for (Directory directory : this.directories) {
                directories.addAll(directory.walkDirectories());
            }

            return directories;
        }

        public List<File> walkFiles() {
            List<File> files = new ArrayList<>(this.files);
            for (Directory directory : this.walkDirectories()) {
                files.addAll(directory.files());
            }
            return files;
        }
    }

    private record File(String name, long size) {
        public void print(int tabs) {
            String prefix = TAB.repeat(tabs);
            System.out.println(prefix + "- " + this.name + " (file, size=" + this.size + ")");
        }
    }
}