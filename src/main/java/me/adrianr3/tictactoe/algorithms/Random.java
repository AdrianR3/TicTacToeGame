package me.adrianr3.tictactoe.algorithms;

import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;

import java.util.ArrayList;
import java.util.List;

public class Random implements TicTacToeAlgorithm {

    @Override
    public Coord onMove(GameBoard board, int playerInteger) {
        int[][] boardArray = board.getBoardArray();

        List<Coord> zeros = new ArrayList<>();

        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[i].length; j++) {
                if (boardArray[i][j] == 0) {
                    zeros.add(new Coord(j, i));
                }
            }
        }

        if (zeros.isEmpty()) throw new IllegalStateException("No possible move found");

        return zeros.get(new java.util.Random().nextInt(zeros.size()));
    }

    @Override
    public void onGameEnd(Referee.Result result) {

    }
}
