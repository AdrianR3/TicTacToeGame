package me.adrianr3.tictactoe.game;

import me.adrianr3.tictactoe.board.GameBoard;

public class Referee {

    public static Result checkBoard(GameBoard board) {

        int[][] boardArray = board.getBoardArray();

        GameBoard.Player diagonals = checkDiagonals(boardArray);
        if (diagonals != GameBoard.Player.NOBODY) {
            return Result.getFromPlayer(diagonals);
        }

        GameBoard.Player rows = checkRows(boardArray);
        if (rows != GameBoard.Player.NOBODY) {
            return Result.getFromPlayer(rows);
        }

        GameBoard.Player columns = checkColumns(boardArray);
        if (columns != GameBoard.Player.NOBODY) {
            return Result.getFromPlayer(columns);
        }

        if (checkTie(boardArray)) return Result.TIE;

        return Result.NOOP;
    }

    private static boolean checkTie(int[][] board) {
        for (int[] row : board) {
            for (int columnInt : row) {
                if (columnInt == 0) return false;
            }
        }

        return true;
    }

    private static GameBoard.Player checkRows(int[][] board) {
        int player = 0;

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] != board[y][0]) { player = 0; break; }
                player = board[y][x];
            }
            if (player != 0) break;
        }

        return GameBoard.Player.fromBoardValue(player);
    }

    private static GameBoard.Player checkColumns(int[][] board) {
        int player = 0;

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (board[y][x] != board[0][x]) { player = 0; break; }
                player = board[y][x];
            }
            if (player != 0) break;
        }

        return GameBoard.Player.fromBoardValue(player);
    }

    private static GameBoard.Player checkDiagonals(int[][] board) {
        int player = 0;

//        Check main diagonal from top left
        for (int i = 0; i < Math.min(board.length, board[0].length); i++) {
            if (board[i][i] != board[0][0]) {
                player = 0;
                break;
            }
            player = board[0][0];
        }

        if (player != 0) return GameBoard.Player.fromBoardValue(player);

//        Check reverse diagonal from top right
        for (int x = Math.min(board.length, board[0].length)-1; x > 0; x--) {
            int y = board[0].length - 1 - x;
            if (board[y][x] != board[board[0].length - 1][0]) break;
            player = board[0][board[y].length - 1];
        }

        return GameBoard.Player.fromBoardValue(player);
    }

    public enum Result {
        PLAYER_WIN, TIE, OPPONENT_WIN, NOOP;

        public static Result getFromPlayer(GameBoard.Player player) {
            switch (player) {
                case SELF -> {
                    return PLAYER_WIN;
                }
                case OPPONENT -> {
                    return OPPONENT_WIN;
                }
                default -> {
                    return NOOP;
                }
            }
        }

    }
}
