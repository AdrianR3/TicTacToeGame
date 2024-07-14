package me.adrianr3.tictactoe.algorithms;

import java.lang.reflect.InvocationTargetException;

public enum Algorithm {

    PLAYER(ManualPlayer.class, "Manual Player"), MINIMAX(Minimax.class, "Minimax"), RANDOM(Random.class, "Random");

    private int algorithmId;
    private final String name;
    private final Class<? extends TicTacToeAlgorithm> algorithm;

    private static int nextId = 0;

    Algorithm(Class<? extends TicTacToeAlgorithm> algorithmClazz, String name) {
        this.algorithm = algorithmClazz;
        this.name = name;

        assignAlgorithmId();
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

    public String getName() {
        return name;
    }

    private void assignAlgorithmId() {
        this.algorithmId = nextId++;
    }
}
