package me.adrianr3.tictactoe.algorithms;

import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;

public interface TicTacToeAlgorithm {
    Coord onMove(GameBoard board, int playerInteger);
    void onGameEnd(Referee.Result result);
}
