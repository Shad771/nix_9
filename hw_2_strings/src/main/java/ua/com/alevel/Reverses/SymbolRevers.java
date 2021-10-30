package ua.com.alevel.Reverses;

import ua.com.alevel.StringHelperUtil;
import ua.com.alevel.Task;

import java.io.BufferedReader;
import java.io.IOException;

public class SymbolRevers {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("Revers Symbol");
        do {
            System.out.print("Input your string: ");
            String str = input.readLine();
            System.out.print("Enter first symbol: ");
            String firstSymbol = input.readLine();
            System.out.print("Enter last symbol: ");
            String lastSymbol = input.readLine();

            str = StringHelperUtil.reverse(str, firstSymbol.charAt(0), lastSymbol.charAt(0));
            System.out.println(str);

            System.out.println("Want to continue? (y/n)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("y"));

        new Task().run();
    }
}