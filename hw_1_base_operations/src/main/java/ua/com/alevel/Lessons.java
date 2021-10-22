package ua.com.alevel;

import java.util.Scanner;

public class Lessons {
    public void lessons() {
        System.out.println("Third task!");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter lesson number: ");
        int lessonNum = scanner.nextInt();

        int lessonTime = lessonNum * 45 + (lessonNum / 2) * 5 + ((lessonNum + 1) / 2 - 1) * 15;
        System.out.println("Lesson time: " + (lessonTime / 60 + 9) + ":" + (lessonTime % 60));

    }
}