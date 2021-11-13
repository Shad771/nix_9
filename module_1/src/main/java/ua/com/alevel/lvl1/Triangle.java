package ua.com.alevel.lvl1;

import ua.com.alevel.TaskHelper;

import java.awt.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Triangle {

    public static void run(Scanner scanner) throws IOException {
        String again = "";
        Point[] points = new Point[3];
        do {
            while (true) {
                try {
                    System.out.println("Enter the coordinates of the vertices of the triangle");
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Input coordinates of " + (i + 1) + " th point:");
                        System.out.print("X: ");
                        int x = scanner.nextInt();
                        System.out.print("Y: ");
                        int y = scanner.nextInt();
                        System.out.println();
                        Point point = new Point(x, y);
                        points[i] = point;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input. Try again");
                    scanner.nextLine();
                    continue;
                }
                break;
            }
            double ab = distance(points[0], points[1]);
            double bc = distance(points[1], points[2]);
            double ca = distance(points[0], points[2]);
            if (triangle(ab, bc, ca)) {
                double s = (ab + bc + ca) / 2;
                String triangle = String.format("%.1f", Math.sqrt(s * (s - ab) * (s - bc) * (s - ca)));
                System.out.println("Area of this triangle " + triangle);
            }
            scanner.nextLine();
            System.out.println("Do you want to continue? (Y/N)");
            again = scanner.nextLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));
        new TaskHelper().lvl1(scanner);
    }

    static double distance(Point p1, Point p2) {
        double distanceX = p1.x - p2.x;
        double distanceY = p1.y - p2.y;
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    public static boolean triangle(double m, double n, double u) {
        if ((m + n > u) && (n + u > m) && (m + u > n)) {
            return true;
        } else {
            System.out.println("The triangle does NOT exist.");
            return false;
        }
    }
}
