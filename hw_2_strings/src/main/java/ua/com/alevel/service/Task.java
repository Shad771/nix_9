package ua.com.alevel.service;

import ua.com.alevel.service.reverses.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task {

    public static void run() {
        helper();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice;

        try {
            while ((choice = input.readLine()) != null) {
                switch (choice) {
                    case "1":
                        new Revers().run(input);
                        break;
                    case "2":
                        new SubstringRevers().run(input);
                        break;
                    case "3":
                        new IndexRevers().run(input);
                        break;
                    case "4":
                        new SymbolRevers().run(input);
                        break;
                    case "5":
                        new StringsRevers().run(input);
                        break;
                    case "0":
                        System.out.println("Exit");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect value. Please, try again.");
                        System.out.println("Task number ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void helper() {
        System.out.println("Menu");
        System.out.println("Tasks");
        System.out.println("1 - Reverse");
        System.out.println("2 - Substring reverse");
        System.out.println("3 - Index reverse ");
        System.out.println("4 - Symbols reverse");
        System.out.println("5 - String reverse");
        System.out.println("0 - Exit");
        System.out.print("Task number ");
    }
}