package me.imspooks.adventofcode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = "E:\\Code\\Java\\Nick\\AdventOfCode\\AoC-2025\\src\\main\\resources\\aoc-2025\\day-2\\input.txt";
        try {
            Scanner fileIn = new Scanner(new File(input));
            ArrayList<Long> invalids = new ArrayList<>();
            ArrayList<Long> invalids2 = new ArrayList<>();
            ArrayList<Long> initials = new ArrayList<>();
            ArrayList<Long> finals = new ArrayList<>();
            ArrayList<String> ids = new ArrayList<>();
            long part1 = 0;
            long part2 = 0;
            long part2Sub = 0;
            while (fileIn.hasNext()) {
                String lineIn = fileIn.nextLine();
                String id = "";
                for (int i = 0; i < lineIn.length(); i++) {
                    if (lineIn.substring(i,i+1).equals(",")) {
                        ids.add(id);
                        id = "";
                    }
                    else {
                        id += lineIn.substring(i,i+1);
                    }
                }
                ids.add(id);
            }
            System.out.println(ids);
            ArrayList<String> starts = new ArrayList<>();
            for (int i = 0; i < ids.size(); i++) {
                starts.add(ids.get(i).substring(0,ids.get(i).indexOf("-")));
            }
            ArrayList<String> ends = new ArrayList<>();
            for (int i = 0; i < ids.size(); i++) {
                ends.add(ids.get(i).substring(ids.get(i).indexOf("-")+1));
            }
            for (int i = 0; i < starts.size(); i++) {
                initials.add(Long.parseLong(starts.get(i)));
            }
            for (int i = 0; i < ends.size(); i++) {
                finals.add(Long.parseLong(ends.get(i)));
            }
            for (int i = 0; i < starts.size(); i++) {
                for (long j = initials.get(i); j <= finals.get(i); j++) {
                    String test = Long.toString(j);
                    if (test.substring(0, test.length()/2).equals(test.substring(test.length()/2))) {
                        invalids.add(Long.parseLong(test));

                    }
                    int digits = test.length();
                    ArrayList<Integer> factors = getFactors(digits);
                    for (int m = 0; m < factors.size(); m++) {
                        int subdivision = digits/factors.get(m);
                        ArrayList<String> divisions = new ArrayList<>();
                        for (int n = 0; n < digits; n+=subdivision) {
                            divisions.add(test.substring(n,n+subdivision));
                        }
                        boolean isInvalid = true;
                        for (int p = 1; p < divisions.size(); p++) {
                            if (!(divisions.get(p).equals(divisions.get(0)))) {
                                isInvalid = false;
                            }
                        }
                        if (isInvalid) {
                            invalids2.add(Long.parseLong(test));
                            System.out.println(starts.get(i) + "-" + ends.get(i) + " = " + test);
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < invalids.size(); i++) {
                part1 += invalids.get(i);
            }
            System.out.println(starts);
            System.out.println(ends);
            System.out.println(initials);
            System.out.println(finals);
            System.out.println(invalids);
            System.out.println(part1);
            for (int i = 0; i < invalids2.size(); i++) {
                part2 += invalids2.get(i);
            }
            System.out.println(invalids2);
            System.out.println(part2);
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
    public static int getDigits(long number) {
        int result = 0;
        while (number > 0) {
            number /= 10;
            result += 1;
        }
        return result;
    }
    public static ArrayList<Integer> getFactors(int number){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (number%i == 0) result.add(i);
        }
        return result;
    }
}