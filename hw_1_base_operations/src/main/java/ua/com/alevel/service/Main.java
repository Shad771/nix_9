package ua.com.alevel.service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SearchSum firstTask = new SearchSum();
        NORL secondTask = new NORL();
        Lessons thirdTask = new Lessons();
        int i;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select task: ");
        System.out.println("1 - sum of all numbers in string");
        System.out.println("2 - amount of letters in strings");
        System.out.println("3 - time of lesson");
        i = scanner.nextInt();

        switch (i) {
            case 1:
                firstTask.searchSum();
                break;
            case 2:
                secondTask.nORL();
                break;
            case 3:
                thirdTask.lessons();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }
}
