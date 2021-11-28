package ua.com.alevel.service.reverses;

import ua.com.alevel.service.StringHelperUtil;
import ua.com.alevel.service.Task;

import java.io.BufferedReader;
import java.io.IOException;

public class IndexRevers {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("Index Revers");

        do {
            System.out.print("Input your string: ");
            String str = input.readLine();
            System.out.print("Enter first index: ");
            int firstIndex = Integer.parseInt(input.readLine());
            System.out.print("Enter last index: ");
            int lastIndex = Integer.parseInt(input.readLine());
            if (firstIndex >= lastIndex || lastIndex > str.length() || firstIndex < 0) {
                System.out.println("Enter correct index");
                return;
            }
            str = StringHelperUtil.reverse(str, firstIndex, lastIndex);
            System.out.println(str);

            System.out.println("Want to continue? (y/n)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("y"));

        new Task().run();
    }
}
