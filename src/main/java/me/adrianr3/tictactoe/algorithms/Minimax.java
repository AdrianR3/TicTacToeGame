package me.adrianr3.tictactoe.algorithms;

import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;

public class Minimax extends TicTacToeAlgorithm {

    private int selfPlayer;

    @Override
    public Coord onMove(GameBoard board) {
        int[][] boardArray = board.getBoardArray();
        selfPlayer = getPlayerInt();

        return minimax(boardArray, selfPlayer).getCoord();
    }

    @Override
    public void onGameEnd(Referee.Result result) {}

    private PotentialMove minimax(int[][] board, int testPlayer) {
        int optimalScore = testPlayer == selfPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        Coord optimalMoveCoord = null;
        for (int x = 0; x < Math.min(board.length, board[0].length); x++) {
            for (int y = 0; y < Math.min(board.length, board[0].length); y++) {
                if (board[y][x] != 0) continue;

                board[y][x] = testPlayer;
                int score = minimaxScore(board, testPlayer);

//                    Undo the move
                board[y][x] = 0;

                if (testPlayer == selfPlayer && score > optimalScore) {
                    optimalScore = score;
                    optimalMoveCoord = new Coord(x, y);
                } else if (testPlayer != selfPlayer && score < optimalScore) {
                    optimalScore = score;
                    optimalMoveCoord = new Coord(x, y);
                }
            }
        }

        return new PotentialMove(optimalScore, optimalMoveCoord);
    }

    private int minimaxScore(int[][] board, int currentPlayer) {
        if (checkWin(board, selfPlayer)) return 1;
        if (checkWin(board, 3 - selfPlayer)) return -1;
        if (Referee.checkTie(board)) return 0;

//        3 - selfPlayer is quick way to flip between 1 and 2
        return minimax(board, currentPlayer == selfPlayer ? 3 - selfPlayer : selfPlayer).getScore();
    }

    private boolean checkWin(int[][] board, int player) {
        return Referee.checkBoard(board, player) == Referee.Result.PLAYER_WIN;
    }

    private static class PotentialMove {
        int score;
        Coord coord;

        PotentialMove(int score, Coord coord) {
            this.score = score;
            this.coord = coord;
        }

        int getScore() {
            return score;
        }

        Coord getCoord() {
            return coord;
        }
    }
}
