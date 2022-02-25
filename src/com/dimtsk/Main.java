package com.dimtsk;

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

        Pattern pattern1 = Pattern.compile("^(?<Team>\\w+) \\((?<Gender>\\w)\\) (?<Level>B|C|Amateur) (?<AgeGroup>U\\d+|Adult)$");
        Pattern pattern2 = Pattern.compile("^(?<Team>\\w+) (?<Level>B|C|Amateur) (?<AgeGroup>U\\d+|Adult)$$");
        Pattern pattern21 = Pattern.compile("^(?<Team>\\w+) \\((?<Gender>\\w)\\) (?<Level>B|C|Amateur)$");
        Pattern pattern22 = Pattern.compile("^(?<Team>\\w+) \\((?<Gender>\\w)\\) (?<AgeGroup>U\\d+|Adult)$");
        Pattern pattern3 = Pattern.compile("^(?<Team>\\w+) \\((?<Gender>\\w)\\)$");
        Pattern pattern31 = Pattern.compile("^(?<Team>\\w+) (?<Level>B|C|Amateur)$");
        Pattern pattern32 = Pattern.compile("^(?<Team>\\w+) (?<AgeGroup>U\\d+|Adult)$");
        Pattern pattern4 = Pattern.compile("^(?<Team>\\w+)$");

        Matcher matcher1 = pattern1.matcher(s);
        Matcher matcher2 = pattern2.matcher(s);
        Matcher matcher21 = pattern21.matcher(s);
        Matcher matcher22 = pattern22.matcher(s);
        Matcher matcher3 = pattern3.matcher(s);
        Matcher matcher31 = pattern31.matcher(s);
        Matcher matcher32 = pattern32.matcher(s);
        Matcher matcher4 = pattern4.matcher(s);

        if (matcher1.find()) {
            return String.format("%s (%s) %s %s", matcher1.group("Team"), matcher1.group("Gender"), matcher1.group("Level"), matcher1.group("AgeGroup"));
        }
        if (length == 3) {
            if (matcher2.find()) {
                return String.format("%s %s %s", matcher2.group("Team"), matcher2.group("Level"), matcher2.group("AgeGroup"));
            } else if (matcher21.find()) {
                return String.format("%s (%s) %s", matcher21.group("Team"), matcher21.group("Gender"), matcher21.group("Level"));
            } else if (matcher22.find()) {
                return String.format("%s (%s) %s", matcher22.group("Team"), matcher22.group("Gender"), matcher22.group("AgeGroup"));
            } else {
                System.out.println("Match not found");
            }
        }

        if (length == 2) {
            if (matcher3.find()) {
                return String.format("%s (%s)", matcher3.group("Team"), matcher3.group("Gender"));
            } else if (matcher31.find()) {
                return String.format("%s %s", matcher31.group("Team"), matcher31.group("Level"));
            } else if (matcher32.find()) {
                return String.format("%s %s", matcher32.group("Team"), matcher32.group("AgeGroup"));
            } else {
                System.out.println("Match not found");
            }
        }

        if (matcher4.find()) {
            return String.format("%s", matcher4.group("Team"));
        }
        return result;

    }
}
