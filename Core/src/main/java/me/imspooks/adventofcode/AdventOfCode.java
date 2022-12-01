package me.imspooks.adventofcode;

import me.imspooks.adventofcode.interfaces.Day;
import me.imspooks.adventofcode.interfaces.Part;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by Nick on 03 Dec 2021.
 * Copyright © ImSpooks
 */
public class AdventOfCode {

    public AdventOfCode(String[] args) {
        int year = -1;
        int day = -1;
        int part = -1;

        if (args.length > 0) {
            year = Integer.parseInt(args[0]);
            day = args.length > 1 ? Integer.parseInt(args[1]) : -1;;
            part = args.length > 2 ? Integer.parseInt(args[2]) : -1;;
        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter a year: ");
            year = Integer.parseInt(scanner.next());

            System.out.print("Enter a day: ");
            day = Integer.parseInt(scanner.next());

            System.out.print("Enter a part: ");
            part = Integer.parseInt(scanner.next());
        }

        try {
            this.runChallenge(year, day, part);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void runChallenge(int yearNr, int dayNr, int partNr) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("me.imspooks.adventofcode.v" + yearNr + ".Day" + dayNr);

        if (!Day.class.isAssignableFrom(clazz)) {
            return;
        }

        Day day = (Day) clazz.getConstructor().newInstance();
        day.preRun();

        for (Method method : clazz.getMethods()) {
            Part part = method.getAnnotation(Part.class);
            if (part != null && (partNr == -1 || part.part() == partNr)) {
                Object result = method.invoke(day);

                System.out.print("**Result for day " + dayNr + " part " + part.part() + ":** ");
                System.out.println(result);
            }
        }
    }

    public static void main(String[] args) {
        new AdventOfCode(args);
    }
}