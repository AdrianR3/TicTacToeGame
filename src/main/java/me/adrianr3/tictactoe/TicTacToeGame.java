package me.adrianr3.tictactoe;

import me.adrianr3.tictactoe.algorithms.Algorithm;
import me.adrianr3.tictactoe.algorithms.TicTacToeAlgorithm;
import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;
import me.adrianr3.tictactoe.util.Colors;
import me.adrianr3.tictactoe.util.PrintUtil;
import me.adrianr3.tictactoe.util.ScannerUtil;

import java.util.Scanner;

public class TicTacToeGame {

    static GameBoard board;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print(Colors.CYAN+"Game size: "+Colors.RESET);
        int size = s.nextInt();
        System.out.println();

        System.out.println(Colors.CYAN+"Please select player types: "+Colors.RESET);
        System.out.println(Colors.RED_BRIGHT+"Manual Player "+Colors.RESET+"(0)");
        System.out.println(Colors.RED_BRIGHT+"Minimax "+Colors.RESET+"(1)");
        System.out.println(Colors.RED_BRIGHT+"Random "+Colors.RESET+"(2)");
        System.out.println();

        int player1Type = ScannerUtil.getAlgorithmId("Player 1: ");
        int player2Type = ScannerUtil.getAlgorithmId("Player 2: ");

        TicTacToeAlgorithm player1 = Algorithm.fromAlgorithmId(player1Type).getAlgorithmInstance(1);
        TicTacToeAlgorithm player2 = Algorithm.fromAlgorithmId(player2Type).getAlgorithmInstance(2);

        do {
            System.out.println();

            System.out.println(Colors.BLUE_BRIGHT+ playGame(size, player1, player2, false) +Colors.RESET);

            System.out.print(Colors.CYAN+"Play again? "+Colors.RESET+"(y) ");
        } while (s.next().equalsIgnoreCase("y"));

    }

    private static Referee.Result playGame(int size, TicTacToeAlgorithm player1, TicTacToeAlgorithm player2, boolean silent) {

        board = new GameBoard(size);
        silent = !silent;

        if (silent) PrintUtil.printBoard(board, System.out);

        do {
            System.out.println();

            if (silent) System.out.println(Colors.GREEN+"Your move: "+Colors.RESET);
            board.performMove(player1.onMove(board), GameBoard.Player.SELF);
//            if (silent) PrintUtil.printBoard(board, System.out);

            if (Referee.checkBoard(board, 1) != Referee.Result.NOOP) break;

            System.out.println();

            if (silent) System.out.println(Colors.PURPLE+"Their move: "+Colors.RESET);
            board.performMove(player2.onMove(board), GameBoard.Player.OPPONENT);
            if (silent) PrintUtil.printBoard(board, System.out);


        } while (Referee.checkBoard(board, 1) == Referee.Result.NOOP);

        if (silent) System.out.println();

        Referee.Result result = Referee.checkBoard(board, 1);

        switch (result) {
            case NOOP -> throw new IllegalStateException("Outcome cannot be NOOP");
            case TIE -> { if (silent) System.out.println("Game Tied"); }
            case PLAYER_WIN -> { if (silent) System.out.println(Colors.GREEN_BOLD + "Player 1 won!" + Colors.RESET); }
            case OPPONENT_WIN -> { if (silent) System.out.println(Colors.RED_BOLD + "Player 1 lost! :(" + Colors.RESET); }
        }

        if (silent) PrintUtil.printBoard(board, System.out);

        return result;

    }

    public static boolean checkMove(GameBoard board, Coord coord) {
         return board.getBoardArray()[coord.y][coord.x] == 0;
    }

}