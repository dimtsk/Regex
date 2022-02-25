package com.dimtsk;

import java.util.ArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String a = "Test_Team2 (F) B U14";
        String b = "Test_Team2 B U14";
        String c = "Test_Team2 (F) B";
        String d = "Test_Team2 (F) U14";
        String e = "Test_Team2 (F)";
        String f = "Test_Team2 B";
        String g = "Test_Team2 U14";
        String h = "Test_Team2";

        System.out.println("Actual: " + a + "  --> Expected: " + getStringPattern(a));
        System.out.println("Actual: " + b + "  --> Expected: " + getStringPattern(b));
        System.out.println("Actual: " + c + "  --> Expected: " + getStringPattern(c));
        System.out.println("Actual: " + d + "  --> Expected: " + getStringPattern(d));
        System.out.println("Actual: " + e + "  --> Expected: " + getStringPattern(e));
        System.out.println("Actual: " + f + "  --> Expected: " + getStringPattern(f));
        System.out.println("Actual: " + g + "  --> Expected: " + getStringPattern(g));
        System.out.println("Actual: " + h + "  --> Expected: " + getStringPattern(h));
    }

    public static String getStringPattern(String s) {
        String result = "";
        int length = s.split(" ").length;
        List<Pattern> patternsList = getPattern();
//        List<Matcher> match = getmatchPattern1(patternsList, s);

        for (Pattern pattern : patternsList) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find() && length == 4) {
                return String.format("%s (%s) %s %s", matcher.group("Team"), matcher.group("Gender"), matcher.group("Level"), matcher.group("AgeGroup"));
            }
            if (length == 3) {
                if (matcher.find()) {
                    return String.format("%s %s %s", matcher.group("Team"), matcher.group("Level"), matcher.group("AgeGroup"));
                } else if (matcher.find()) {
                    return String.format("%s (%s) %s", matcher.group("Team"), matcher.group("Gender"), matcher.group("Level"));
                } else if (matcher.find()) {
                    return String.format("%s (%s) %s", matcher.group("Team"), matcher.group("Gender"), matcher.group("AgeGroup"));
                }
            }

            if (length == 2) {
                if (matcher.find()) {
                    return String.format("%s (%s)", matcher.group("Team"), matcher.group("Gender"));
                } else if (matcher.find()) {
                    return String.format("%s %s", matcher.group("Team"), matcher.group("Level"));
                } else if (matcher.find()) {
                    return String.format("%s %s", matcher.group("Team"), matcher.group("AgeGroup"));
                }
            }

            if (matcher.find() && length == 1) {
                return String.format("%s", matcher.group("Team"));
            }
        }
        return result;
    }

    public static List<Pattern> getPattern() {
        List<Pattern> p = List.of(
                Pattern.compile("^(?<Team>\\w+) \\((?<Gender>\\w)\\) (?<Level>B|C|Amateur) (?<AgeGroup>U\\d+|Adult)$"),
                Pattern.compile("^(?<Team>\\w+) (?<Level>B|C|Amateur) (?<AgeGroup>U\\d+|Adult)$"),
                Pattern.compile("^(?<Team>\\w+) \\((?<Gender>\\w)\\) (?<Level>B|C|Amateur)$"),
                Pattern.compile("^(?<Team>\\w+) \\((?<Gender>\\w)\\) (?<AgeGroup>U\\d+|Adult)$"),
                Pattern.compile("^(?<Team>\\w+) \\((?<Gender>\\w)\\)$"),
                Pattern.compile("^(?<Team>\\w+) (?<Level>B|C|Amateur)$"),
                Pattern.compile("^(?<Team>\\w+) (?<AgeGroup>U\\d+|Adult)$"),
                Pattern.compile("^(?<Team>\\w+)$")
        );
        return p;
    }

    public static Pattern matchPattern(List<Pattern> p, String s) {
        Matcher m;
        Pattern ptr = null;

        for (Pattern pattern : p) {
            m = pattern.matcher(s);
            if (m.find()) {
                return pattern;
            }
        }
        return ptr;
    }

    public static List<Matcher> getmatchPattern1(List<Pattern> p, String s) {
        List<Matcher> matcher = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            Matcher m = p.get(i).matcher(s);
            matcher.add(m);
        }
        return matcher;
    }
}
