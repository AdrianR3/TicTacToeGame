package me.adrianr3.tictactoe.board;

import java.util.Arrays;

@SuppressWarnings("unused")
public class GameBoard {

    private int[][] board;
    private final int size;

    public GameBoard(int size) {
        this.size = size;
        board = new int[size][size];
    }

    public GameBoard(int size, int[][] board) {
        this.size = size;
        this.board = board;
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
        board[coord.y][coord.x] = player.getBoardValue();
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
        NOBODY(0), SELF(1), OPPONENT(2);

        private final int boardValue;
        Player(int boardValue) {
            this.boardValue = boardValue;
        }

        public int getBoardValue() {
            return boardValue;
        }

        public static Player fromBoardValue(int boardValue) {
            for (Player player : values()) {
                if (player.getBoardValue() == boardValue) {
                    return player;
                }
            }
            throw new IllegalArgumentException(boardValue + " is not a board value.");
        }

        public static Player fromPlayerInteger(int boardValue, int selfInteger) {
            if (boardValue == 0) return NOBODY;
            return boardValue == selfInteger ? SELF : OPPONENT;
        }

    }
}
