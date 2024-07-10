package me.adrianr3.tictactoe;

import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;
import me.adrianr3.tictactoe.util.PrintUtil;
import me.adrianr3.tictactoe.util.ScannerUtil;
import me.adrianr3.tictactoe.board.Coord;

import java.util.Scanner;

public class TicTacToeGame {

    static GameBoard board;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Game size: ");
        board = new GameBoard(s.nextInt());

        while (Referee.checkBoard(board) == Referee.Result.NOOP) {

            PrintUtil.printBoard(board, System.out);

            System.out.println("Your move: ");
            Coord moveCoord;

            do {
                moveCoord = ScannerUtil.getPlayerMove(board.getSize());
            } while (!checkMove(board, moveCoord));

            board.performMove(moveCoord, GameBoard.Player.SELF);

            if (Referee.checkBoard(board) != Referee.Result.NOOP) break;

//            TODO: Add opponent algorithm

        }

        switch (Referee.checkBoard(board)) {
            case NOOP -> throw new IllegalStateException("Outcome cannot be NOOP");
            case TIE -> System.out.println("Game Tied");
            case PLAYER_WIN -> System.out.println(Colors.GREEN_BOLD + "You won!" + Colors.RESET);
            case OPPONENT_WIN -> System.out.println(Colors.RED_BOLD + "You lost! :(" + Colors.RESET);
        }

    }

    static boolean checkMove(GameBoard board, Coord coord) {
         return board.getBoardArray()[coord.x][coord.y] == 0;
    }

}