package ua.com.alevel.service.reverses;

import ua.com.alevel.service.StringHelperUtil;
import ua.com.alevel.service.Task;

import java.io.BufferedReader;
import java.io.IOException;

public class Revers {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("Revers");

        do {
            System.out.print("Input your string: ");
            String str = input.readLine();

            str = StringHelperUtil.reverse(str, true);
            System.out.println(str);
            str = StringHelperUtil.reverse(str, true);
            str = StringHelperUtil.reverse(str, false);
            System.out.println(str);

            System.out.println("Want to continue? (y/n)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("y"));

        new Task().run();
    }
}