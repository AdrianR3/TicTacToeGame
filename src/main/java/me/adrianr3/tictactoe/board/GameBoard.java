package me.adrianr3.tictactoe.board;

import java.util.Arrays;

public class GameBoard {

    private int[][] board;
    private final int size;

    public GameBoard(int size) {
        this.size = size;
        board = new int[size][size];
    }

    public int[][] getBoardArray() {
        return board;
    }

    public void clear() {
        board = new int[size][size];
    }

    public int getSize() {
        return size;
    }

    public void performMove(Coord coord, Player player) {
        board[coord.x][coord.y] = player.getBoardValue();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GameBoard{\n");
        for (int[] row : board) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        sb.append('}');
        return sb.toString();
    }

    public enum Player {
        SELF(1), OPPONENT(2);

        private final int boardValue;
        Player(int boardValue) {
            this.boardValue = boardValue;
        }

        public int getBoardValue() {
            return boardValue;
        }

    }
}
