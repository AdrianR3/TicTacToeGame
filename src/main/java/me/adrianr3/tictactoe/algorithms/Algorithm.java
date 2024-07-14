package me.adrianr3.tictactoe.algorithms;

import java.lang.reflect.InvocationTargetException;

public enum Algorithm {

    PLAYER(ManualPlayer.class, 0), MINIMAX(Minimax.class, 1), RANDOM(Random.class, 2);

    private final int algorithmId;
    private final Class<? extends TicTacToeAlgorithm> algorithm;

    Algorithm(Class<? extends TicTacToeAlgorithm> algorithmClazz, int id) {
        this.algorithmId = id;
        this.algorithm = algorithmClazz;
    }

    public TicTacToeAlgorithm getAlgorithmInstance(int playerInteger) {
        try {
            return algorithm.getDeclaredConstructor().newInstance().setPlayerInt(playerInteger);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Algorithm fromAlgorithmId(int algorithmId) {
        for (Algorithm algorithm : values()) {
            if (algorithm.getAlgorithmId() == algorithmId) {
                return algorithm;
            }
        }
        throw new IllegalArgumentException(algorithmId + " does not refer to a valid algorithm.");
    }

    public int getAlgorithmId() {
        return algorithmId;
    }
}
