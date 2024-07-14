package me.adrianr3.tictactoe.algorithms;

import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;

import java.util.ArrayList;
import java.util.List;

public class Random extends TicTacToeAlgorithm {

    @Override
    public Coord onMove(GameBoard board) {
        int[][] boardArray = board.getBoardArray();

        List<Coord> zeros = new ArrayList<>();

        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                if (boardArray[y][x] == 0) {
                    zeros.add(new Coord(x, y));
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
