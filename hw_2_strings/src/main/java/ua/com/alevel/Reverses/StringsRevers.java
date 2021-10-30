package ua.com.alevel.Reverses;


import ua.com.alevel.StringHelperUtil;
import ua.com.alevel.Task;

import java.io.BufferedReader;
import java.io.IOException;

public class StringsRevers {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("String Revers");

        do {
            System.out.print("Input your string: ");
            String str = input.readLine();
            System.out.print("Input first string: ");
            String first = input.readLine();
            System.out.print("Input last string: ");
            String last = input.readLine();

            str = StringHelperUtil.reverse(str, first, last);
            System.out.println(str);

            System.out.println("Want to continue? (y/n)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("y"));

        new Task().run();
    }
}