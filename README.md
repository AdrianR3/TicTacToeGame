# CLI Tic Tac Toe

A full Java implementation of the Tic Tac Toe game, playable in the command line.

Supports matching any combination of a manual player, a random player, and minimax.
> Pro Tip: Try playing a friend by setting both players as `Manual Player` (ID: 0)

Example:
`Game size: 3`
```
   0, 1, 2 
0 [0, 0, 0]
1 [0, 0, 0]
2 [0, 0, 0]
```

## Screenshots

<img width="220" alt="Tic Tac Toe running in the CLI" src="https://github.com/user-attachments/assets/0e258336-addf-474b-bbc4-2fe4945cdf37">


## Adding Another Algorithm

To add another algorithm to the game, create a new class in `tictactoe.algorithms`. This class can be named however you like, but it must extend `TicTacToeAlgorithm`.


```java

public class MyAlgorithm extends TicTacToeAlgorithm {
    @Override
    public Coord onMove(GameBoard board) {
        int[][] boardArray = board.getBoardArray();

        return new Coord(x, y);
    }
}
```

To register your algorithm's class, add an enum value to the `Algorithm` enum in `tictactoe.algorithms.Algorithm.java` with a title to be shown to the user:

```java
public enum Algorithm {

//  Built-In algorithms
    PLAYER(ManualPlayer.class, "Manual Player"), MINIMAX(Minimax.class, "Minimax"), RANDOM(Random.class, "Random"),

//  Custom Algorithms
    MYALGORITHM(MyAlgorithm.class, "My Algorithm");

    // ...

}

```

