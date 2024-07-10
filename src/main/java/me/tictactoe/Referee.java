package me.tictactoe;

public class Referee {

    public static Result checkBoard(GameBoard board) {
//        TODO: Implement board checking
        return Result.NOOP;
    }

    public enum Result {
        PLAYER_WIN, TIE, OPPONENT_WIN, NOOP
    }
}
