package me.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

    static GameBoard board;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Game size: ");
        board = new GameBoard(s.nextInt());

//        TODO: Check if a player has won/lost
        while (true) {
            System.out.println("Your move: ");
            Coord moveCoord;

            do {
                moveCoord = ScannerUtil.getPlayerMove(board.getSize());
//                TODO: Check if move is valid
            } while (false);

//            TODO: Check if player has won/lost

//            TODO: Add opponent algorithm

        }

    }

}