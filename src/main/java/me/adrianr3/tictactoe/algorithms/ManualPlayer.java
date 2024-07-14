package me.adrianr3.tictactoe.algorithms;

import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.util.ScannerUtil;

import static me.adrianr3.tictactoe.TicTacToeGame.checkMove;

public class ManualPlayer extends TicTacToeAlgorithm {

    @Override
    public Coord onMove(GameBoard board) {
        Coord moveCoord;
        do {
            moveCoord = ScannerUtil.getPlayerMove(board.getSize());
        } while (!checkMove(board, moveCoord));

        return moveCoord;
    }
}
