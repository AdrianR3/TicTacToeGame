package me.adrianr3.tictactoe.algorithms;

import me.adrianr3.tictactoe.board.Coord;
import me.adrianr3.tictactoe.board.GameBoard;
import me.adrianr3.tictactoe.game.Referee;

public class Minimax implements TicTacToeAlgorithm {
    @Override
    public Coord onMove(GameBoard board, int selfInteger) {
        int[][] boardArray = board.getBoardArray();

        return minimax(boardArray, selfInteger).getCoord();
    }

    @Override
    public void onGameEnd(Referee.Result result) {}

    int selfPlayer = 2;
    int opponent = 1;

    private PotentialMove minimax(int[][] board, int testPlayer) {
        int optimalScore = (testPlayer == selfPlayer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        Coord optimalMoveCoord = null;
        for (int x = 0; x < Math.min(board.length, board[0].length); x++) {
            for (int y = 0; y < Math.min(board.length, board[0].length); y++) {
                if (board[y][x] == 0) {
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
        }

        return new PotentialMove(optimalScore, optimalMoveCoord);
    }

    private int minimaxScore(int[][] board, int currentPlayer) {
        if (checkWin(board, selfPlayer)) return 1;
        if (checkWin(board, opponent)) return -1;
        if (Referee.checkTie(board)) return 0;

        return minimax(board, (currentPlayer == selfPlayer) ? 1 : selfPlayer).getScore();
    }

//    private void quickCheck(int[][] board, int selfPlayer, boolean correctValue) {
//        boolean good = (Referee.checkBoard(board, selfPlayer) == Referee.Result.PLAYER_WIN) == correctValue;
//
//        if (!good) {
//            Referee.checkBoard(board, selfPlayer);
//            throw new IllegalStateException("Poo Poo");
//        }
//    }

    private boolean checkWin(int[][] board, int player) {
        return Referee.checkBoard(board, player) == Referee.Result.PLAYER_WIN;

//        for (int i = 0; i < 3; i++) {
//            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) { quickCheck(board, player, true); return true;}
//            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) { quickCheck(board, player, true); return true;}
//        }
//        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) { quickCheck(board, player, true); return true;}
//        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) { quickCheck(board, player, true); return true;}
//////            if (Referee.checkBoard(board, player) == Referee.Result.PLAYER_WIN) {
////                System.out.println(
////                        Referee.checkBoard(board, player) == Referee.Result.PLAYER_WIN
////                );
//////            }
////            return true;
////        }
//        quickCheck(board, player, false);
//        return false;
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
