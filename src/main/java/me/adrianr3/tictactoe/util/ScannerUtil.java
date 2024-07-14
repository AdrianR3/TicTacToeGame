package me.adrianr3.tictactoe.util;

import me.adrianr3.tictactoe.board.Coord;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {
    public static final Scanner scanner = new Scanner(System.in);

    public static Coord getPlayerMove(int size) {
        int x = -1;
        int y = -1;

        while (x < 0 || x > (size - 1)) {
            try {
                System.out.print(Colors.BLUE+"x: "+Colors.RESET);
                x = scanner.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                scanner.next();
            }
        }

        while (y < 0 || y > (size - 1)) {
            try {
                System.out.print(Colors.RED+"y: "+Colors.RESET);
                y = scanner.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                scanner.next();
            }
        }

        return new Coord(x, y);
    }

    public static int getAlgorithmId(String prompt) {
        int id = -1;

        while (id < 0) {
            try {
                System.out.print(prompt);
                id = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
            }
        }

        return id;
    }
}
