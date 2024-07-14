package me.adrianr3.tictactoe.algorithms;

import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;

public abstract class TicTacToeAlgorithm {

    private int playerInt = 1;

    public abstract Coord onMove(GameBoard board);
    void onGameEnd(Referee.Result result) {}

    public int getPlayerInt() {
        return playerInt;
    }

    public TicTacToeAlgorithm setPlayerInt(int playerInt) {
        this.playerInt = playerInt;
        return this;
    }
}
