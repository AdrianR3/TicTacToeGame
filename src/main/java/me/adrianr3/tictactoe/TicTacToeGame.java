package me.adrianr3.tictactoe;

import me.adrianr3.tictactoe.algorithms.Minimax;
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

        System.out.print("Game size: ");
        int size = s.nextInt();


        do {
            System.out.println();

            playGame(size, false);

            System.out.print("Play again? (y) ");
        } while (s.next().equalsIgnoreCase("y"));

    }

    private static Referee.Result playGame(int size, boolean silent) {

        board = new GameBoard(size);
        silent = !silent;

//        TODO: Option to switch algorithm
        TicTacToeAlgorithm player2 = new Minimax(2);

        do {

            if (silent) PrintUtil.printBoard(board, System.out);

            if (silent) System.out.println("Your move: ");
            Coord moveCoord;

            do {
                moveCoord = ScannerUtil.getPlayerMove(board.getSize());
            } while (!checkMove(board, moveCoord));

            board.performMove(moveCoord, GameBoard.Player.SELF);

            if (Referee.checkBoard(board, 1) != Referee.Result.NOOP) break;

            board.performMove(player2.onMove(board), GameBoard.Player.OPPONENT);

        } while (Referee.checkBoard(board, 1) == Referee.Result.NOOP);

        if (silent) System.out.println();

        Referee.Result result = Referee.checkBoard(board, 1);

        switch (result) {
            case NOOP -> throw new IllegalStateException("Outcome cannot be NOOP");
            case TIE -> { if (silent) System.out.println("Game Tied"); }
            case PLAYER_WIN -> { if (silent) System.out.println(Colors.GREEN_BOLD + "You won!" + Colors.RESET); }
            case OPPONENT_WIN -> { if (silent) System.out.println(Colors.RED_BOLD + "You lost! :(" + Colors.RESET); }
        }

        if (silent) PrintUtil.printBoard(board, System.out);

        return result;

    }

    private static boolean checkMove(GameBoard board, Coord coord) {
         return board.getBoardArray()[coord.y][coord.x] == 0;
    }

}