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
        board = new GameBoard(s.nextInt());

//        TODO: Option to switch algorithm
        TicTacToeAlgorithm opponent = new Minimax(2);

        do {

            PrintUtil.printBoard(board, System.out);

            System.out.println("Your move: ");
            Coord moveCoord;

            do {
                moveCoord = ScannerUtil.getPlayerMove(board.getSize());
            } while (!checkMove(board, moveCoord));

            board.performMove(moveCoord, GameBoard.Player.SELF);

            if (Referee.checkBoard(board, 1) != Referee.Result.NOOP) break;

            board.performMove(opponent.onMove(board), GameBoard.Player.OPPONENT);

        } while (Referee.checkBoard(board, 1) == Referee.Result.NOOP);

        System.out.println();
        switch (Referee.checkBoard(board, 1)) {
            case NOOP -> throw new IllegalStateException("Outcome cannot be NOOP");
            case TIE -> System.out.println("Game Tied");
            case PLAYER_WIN -> System.out.println(Colors.GREEN_BOLD + "You won!" + Colors.RESET);
            case OPPONENT_WIN -> System.out.println(Colors.RED_BOLD + "You lost! :(" + Colors.RESET);
        }

        PrintUtil.printBoard(board, System.out);

//        TODO: Play again option/feature

    }

    static boolean checkMove(GameBoard board, Coord coord) {
         return board.getBoardArray()[coord.y][coord.x] == 0;
    }

}