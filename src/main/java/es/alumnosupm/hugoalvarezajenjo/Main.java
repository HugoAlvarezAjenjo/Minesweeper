package es.alumnosupm.hugoalvarezajenjo;

import es.alumnosupm.hugoalvarezajenjo.model.Board;
import es.alumnosupm.hugoalvarezajenjo.view.BoardListerner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Minesweeper game");
        new BoardListerner(new Board());
    }
}