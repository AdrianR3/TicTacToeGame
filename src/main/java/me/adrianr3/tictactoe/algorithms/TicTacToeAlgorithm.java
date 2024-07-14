package me.adrianr3.tictactoe.algorithms;

import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;

public abstract class TicTacToeAlgorithm {

    private int playerInt = 1;

    public abstract Coord onMove(GameBoard board);
    public void onGameEnd(Referee.Result result) {}

    public final int getPlayerInt() {
        return playerInt;
    }

    public final TicTacToeAlgorithm setPlayerInt(int playerInt) {
        this.playerInt = playerInt;
        return this;
    }
}
