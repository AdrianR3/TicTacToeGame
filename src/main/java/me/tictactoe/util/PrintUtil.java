package me.tictactoe.util;

import me.tictactoe.GameBoard;

import java.io.PrintStream;
import java.util.Arrays;

public class PrintUtil {
    public static void printBoard(GameBoard board, PrintStream sout) {
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        for (int i = 0; i < board.getBoardArray()[0].length; i++) {
            sb.append(i);
            if (i < board.getBoardArray()[0].length - 1) {
                sb.append(',');
                if (i < 10) sb.append(' ');
            }
        }
//        sb.append(" 0, 1, 2 \n");
        sb.append(" \n");
        int i = 0;
        for (int[] row : board.getBoardArray()) {
            sb.append(i);
            sb.append(' ');
            if (i++ < 10) sb.append(' ');
            sb.append(Arrays.toString(row)).append("\n");
        }

        sout.print(sb);
    }
}
