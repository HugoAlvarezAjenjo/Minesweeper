package es.alumnosupm.hugoalvarezajenjo;

import es.alumnosupm.hugoalvarezajenjo.model.Board;
import es.alumnosupm.hugoalvarezajenjo.view.BoardListerner;

/**
 * The `Main` class serves as the entry point of the Minesweeper application.
 * It creates an instance of the `BoardListener` class to start the game.
 *
 * @author Hugo Alvarez Ajenjo
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Minesweeper game");
        new BoardListerner(new Board());
    }
}