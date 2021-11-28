package ua.com.alevel.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NORL {
    public void nORL() {
        System.out.println("Second task!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your string:");
        String input = scanner.nextLine();

        char[] arr = input.toCharArray();
        var chars = new ArrayList<Character>();
        int k = 0;

        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(arr[i])) {
                k++;
                chars.add(arr[i]);
            }
        }

        int charAmount = 1;
        char buf;
        Collections.sort(chars);

        char[] sorted = new char[chars.size()];
        for (int i = 0; i < chars.size(); i++) {
            sorted[i] = chars.get(i);
        }

        for (int i = 0; i < sorted.length - 1; i++) {
            if (sorted[i] == sorted[i + 1] && i != sorted.length - 2) {
                charAmount++;
            } else {
                if (i == sorted.length - 2 && sorted[i] == sorted[i + 1]) {
                    charAmount++;
                    System.out.println(sorted[i] + "=" + charAmount);
                    charAmount = 1;
                } else if (i == sorted.length - 2 && sorted[i] != sorted[i + 1]) {
                    System.out.println(sorted[i] + "=" + charAmount);
                    System.out.println(sorted[i + 1] + "=" + 1);
                } else {
                    System.out.println(sorted[i] + "=" + charAmount);
                    charAmount = 1;
                }
            }
        }
    }
}