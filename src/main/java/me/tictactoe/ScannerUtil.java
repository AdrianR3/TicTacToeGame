package me.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {
    public static final Scanner scanner = new Scanner(System.in);

    public static Coord getPlayerMove(int size) {
        int x = -1;
        int y = -1;

        while (x < 0 || x > (size - 1)) {
            try {
                System.out.print("x: ");
                x = scanner.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                scanner.next();
            }
        }

        while (y < 0 || y > (size - 1)) {
            try {
                System.out.print("y: ");
                y = scanner.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                scanner.next();
            }
        }

        return new Coord(x, y);
    }
}
