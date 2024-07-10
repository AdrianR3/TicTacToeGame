package me.tictactoe;

public class GameBoard {

    private int[][] board;
    private final int size;

    public GameBoard(int size) {
        this.size = size;
        board = new int[size][size];
    }

    public int[][] getBoard() {
        return board;
    }

    public void clear() {
        board = new int[size][size];
    }

    public int getSize() {
        return size;
    }
}
