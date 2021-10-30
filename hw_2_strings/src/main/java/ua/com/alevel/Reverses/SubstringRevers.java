package ua.com.alevel.Reverses;

import ua.com.alevel.StringHelperUtil;
import ua.com.alevel.Task;

import java.io.BufferedReader;
import java.io.IOException;

public class SubstringRevers {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("Substring Revers");

        do {
            System.out.print("Input your string: ");
            String str = input.readLine();
            System.out.print("Input substring: ");
            String substring = input.readLine();

            str = StringHelperUtil.reverse(str, substring);
            System.out.println(str);

            System.out.println("Want to continue? (y/n)");
            again = input.readLine();
            System.out.println();


        } while (again.equalsIgnoreCase("y"));

        new Task().run();
    }
}
